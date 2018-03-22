window.CP.PenTimer.MAX_TIME_IN_LOOP_WO_EXIT = 6000;
var originalImage = null;
var grayImage = null;
var redImage = null;
var raibowImage = null;
var blurryImage = null;
var negativeImage = null;
var mirrorImage = null;
var canvas = document.getElementById("can");

function upload() {
  var file =         document.getElementById("fileInput");
  originalImage = new SimpleImage(file);
  grayImage = new SimpleImage(file);
  redImage = new SimpleImage(file);
  raibowImage = new SimpleImage(file);
  blurryImage = new SimpleImage(file);
  negativeImage = new SimpleImage(file);
  mirrorImage = new SimpleImage(file);
  originalImage.drawTo(canvas);
}
function imageIsLoaded(img) {
  if (img == null || !img.complete()) {
    alert("Image not loaded!");
    return false;
  } else {
    return true;
  }
}
function filterGray() {
  for (var pixel of grayImage.values()) {
    var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    pixel.setRed(avg);
    pixel.setGreen(avg);
    pixel.setBlue(avg);
  }
}
function makeGray() {
  if (imageIsLoaded(grayImage)) {
    filterGray();
    grayImage.drawTo(canvas);
  }
}
function filterRed() {
  for (var pixel of redImage.values()) {
    var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    if (avg < 128) {
      pixel.setRed(2 * avg);
      pixel.setGreen(0);
      pixel.setBlue(0);
    } else {
      pixel.setRed(255);
      pixel.setGreen(2 * avg - 255);
      pixel.setBlue(2 * avg - 255);
    }
  }
}
function makeRed() {
  if (imageIsLoaded(redImage)) {
    filterRed();
    redImage.drawTo(canvas);
  }
}
function filterRainbow() {
  var height = raibowImage.getHeight();
  for (var pixel of raibowImage.values()) {
    var y = pixel.getY();
    var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    if (y < height / 7) {
      //red
      if (avg < 128) {
        pixel.setRed(2 * avg);
        pixel.setGreen(0);
        pixel.setBlue(0);
      } else {
        pixel.setRed(255);
        pixel.setGreen(2 * avg - 255);
        pixel.setBlue(2 * avg - 255);
      }
    } else if (y < height * 2 / 7) {
      //orange
      if (avg < 128) {
        pixel.setRed(2 * avg);
        pixel.setGreen(0.8 * avg);
        pixel.setBlue(0);
      } else {
        pixel.setRed(255);
        pixel.setGreen(1.2 * avg - 51);
        pixel.setBlue(2 * avg - 255);
      }
    } else if (y < height * 3 / 7) {
      //yellow
      if (avg < 128) {
        pixel.setRed(2 * avg);
        pixel.setGreen(2 * avg);
        pixel.setBlue(0);
      } else {
        pixel.setRed(255);
        pixel.setGreen(255);
        pixel.setBlue(2 * avg - 255);
      }
    } else if (y < height * 4 / 7) {
      //green
      if (avg < 128) {
        pixel.setRed(0);
        pixel.setGreen(2 * avg);
        pixel.setBlue(0);
      } else {
        pixel.setRed(2 * avg - 255);
        pixel.setGreen(255);
        pixel.setBlue(2 * avg - 255);
      }
    } else if (y < height * 5 / 7) {
      //blue
      if (avg < 128) {
        pixel.setRed(0);
        pixel.setGreen(0);
        pixel.setBlue(2 * avg);
      } else {
        pixel.setRed(2 * avg - 255);
        pixel.setGreen(2 * avg - 255);
        pixel.setBlue(255);
      }
    } else if (y < height * 6 / 7) {
      //indigo
      if (avg < 128) {
        pixel.setRed(0.8 * avg);
        pixel.setGreen(0);
        pixel.setBlue(2 * avg);
      } else {
        pixel.setRed(1.2 * avg - 51);
        pixel.setGreen(2 * avg - 255);
        pixel.setBlue(255);
      }
    } else {
      //violet
      if (avg < 128) {
        pixel.setRed(1.6 * avg);
        pixel.setGreen(0);
        pixel.setBlue(1.6 * avg);
      } else {
        pixel.setRed(0.4 * avg + 153);
        pixel.setGreen(2 * avg - 255);
        pixel.setBlue(0.4 * avg + 153);
      }
    }
  }
}
function makeRainbow() {
  if (imageIsLoaded(raibowImage)) {
    filterRainbow();
    raibowImage.drawTo(canvas);
  }
}
function ensureInImg(coordinate, size) {
  if (coordinate < 0) {
    return 0;
  }
  if (coordinate >= size) {
    return size - 1;
  }
  return coordinate;
}
function getNearPixel(image, x, y, diameter) {
  var dx = Math.random() * diameter - diameter / 2;
  var dy = Math.random() * diameter - diameter / 2;
  var nx = ensureInImg(x + dx, image.getWidth());
  var ny = ensureInImg(y + dy, image.getHeight());
  return image.getPixel(nx, ny);
}
function filterBlurry() {
  for (var pixel of blurryImage.values()) {
    var x = pixel.getX();
    var y = pixel.getY();
    if (Math.random() > 0.6) {
      var nearPixel = getNearPixel(blurryImage, x, y, 10);
      blurryImage.setPixel(x, y, nearPixel);
    } else {
      blurryImage.setPixel(x, y, pixel);
    }
  }
}
function makeBlurry() {
  if (imageIsLoaded(blurryImage)) {
    filterBlurry();
    blurryImage.drawTo(canvas);
  }
}
function filterNegative() {
  for (var pixel of negativeImage.values()) {
    pixel.setRed(255 - pixel.getRed());
    pixel.setGreen(255 - pixel.getGreen());
    pixel.setBlue(255 - pixel.getBlue());
  }
}
function makeNegative() {
  if (imageIsLoaded(negativeImage)) {
    filterNegative();
    negativeImage.drawTo(canvas);
  }
}

function filterMirror(){
  for (var pixel of mirrorImage.values()) {
var newX = ((mirrorImage.getWidth()-1)-pixel.getX());
var mirrorPixel = originalImage.getPixel(newX,pixel.getY());
pixel.setAllFrom(mirrorPixel);
  }
}
function makeMirrorImage(){
  if (imageIsLoaded(mirrorImage)) {
    filterMirror();
    mirrorImage.drawTo(canvas);
  }
}
function reset() {
  if (imageIsLoaded(originalImage)) {
    originalImage.drawTo(canvas);
    grayImage = new SimpleImage(originalImage);
    redImage = new SimpleImage(originalImage);
    raibowImage = new SimpleImage(originalImage);
    blurryImage = new SimpleImage(originalImage);
    negativeImage = new SimpleImage(originalImage);
    mirrorImage = new SimpleImage(originalImage);
  }
}
function clearCanvas() {
  var ctx = canvas.getContext("2d");
  ctx.clearRect(0, 0, canvas.width, canvas.height);
}