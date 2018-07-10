
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Arrays;
import java.util.HashSet;
import edu.duke.*;

public class Tester {

    public void testCaesarCipher( String fileName, int key ) {
        CaesarCipher cc = new CaesarCipher( key );
        FileResource fr = new FileResource( fileName );
        String rawMessage = fr.asString();
        String encrypted = cc.encrypt( rawMessage );
        String decrypted = cc.decrypt( encrypted );

        System.out.println( "String to Encrypt: \n==============================");
        System.out.println( rawMessage + "\n" );
        System.out.println( "Message encrypted w/ key = " + key + ": \n==============================");
        System.out.println( encrypted + "\n" );
        System.out.println( "Message decrypted w/ key = " + key + ": \n==============================");
        System.out.println( decrypted + "\n" );

        if( rawMessage.equals( decrypted ) ) {
            System.out.println( "The decrypted message matches the unencrypted message. Encryption and decryption succeeded." );
        }
    }

    public void testCaesarCracker( String fileName, char mostCommon ) {
        CaesarCracker cc = new CaesarCracker( mostCommon );
        FileResource fr = new FileResource( fileName );
        String encrypted = fr.asString();
        int key = cc.getKey( encrypted );
        String decrypted = cc.decrypt( encrypted );

        System.out.println( "String to Decrypt: \n==============================");
        System.out.println( encrypted + "\n" );
        System.out.println( "Message decrypted w/ key = " + key + ": \n==============================");
        System.out.println( decrypted + "\n" );
    }

    public void testVigenereCipher( String fileName, int[] key ) {
        VigenereCipher vc = new VigenereCipher( key );
        FileResource fr = new FileResource( fileName );
        String rawMessage = fr.asString();
        String encrypted = vc.encrypt( rawMessage );

        System.out.println( "String to Encrypt: \n==============================");
        System.out.println( rawMessage + "\n" );
        System.out.println( "Message encrypted: \n==============================");
        System.out.println( encrypted + "\n" );
    }

    public void testSliceString( String input, int whichSlice, int totalSlices, String expected ) {
        VigenereBreaker vb = new VigenereBreaker();
        String slice = vb.sliceString( input, whichSlice, totalSlices );
        System.out.println( "Slicing string " + input + " starting with index " + whichSlice + " into " + totalSlices + " chunks." );
        System.out.println( "Result: " + slice );

        if( slice.equals( expected) ) {
            System.out.println( "Obtained the expected slice. Test passes." );
        }
        else {
            System.out.println( "Did not obtain the expected slice. Test fails." );
        }
    }

    public void testSliceStringBattery() {

        testSliceString( "abcdefghijklm", 0, 3, "adgjm" );
        testSliceString( "abcdefghijklm", 1, 3, "behk" );
        testSliceString( "abcdefghijklm", 0, 4, "aeim" );
        testSliceString( "abcdefghijklm", 1, 4, "bfj" );
        testSliceString( "abcdefghijklm", 2, 4, "cgk" );
        testSliceString( "abcdefghijklm", 3, 4, "dhl" );
        testSliceString( "abcdefghijklm", 0, 5, "afk" );
        testSliceString( "abcdefghijklm", 1, 5, "bgl" );
        testSliceString( "abcdefghijklm", 2, 5, "chm" );
        testSliceString( "abcdefghijklm", 3, 5, "di" );
        testSliceString( "abcdefghijklm", 4, 5, "ej" ); 

    }

    public void testTryKeyLength( String fileName, int klength, char mostCommon, int[] expectedKey ) {
        FileResource fr = new FileResource( fileName );
        String message = fr.asString();

        VigenereBreaker vb = new VigenereBreaker();
        int[] guessedKey = vb.tryKeyLength( message, klength, mostCommon );

        System.out.println( "Guessed Key: " + Arrays.toString( guessedKey ) );
        System.out.println( "Expected Key: " + Arrays.toString( expectedKey ) );

        for( int i = 0; i < klength; ++i ) {
            int expected = expectedKey[ i ];
            int actual = guessedKey[ i ];
            if( actual != expected ) {
                System.out.println( "Guessed key does not match expected key. Test fails." );
                return;
            }
        }

        System.out.println( "Guessed Key matches the expected key. Test passes." );
    }

    public void testTryKeyLengthBattery() {
        int[] key = {5, 11, 20, 19, 4};
        testTryKeyLength( "data/athens_keyflute.txt", 5, 'e', key );
    }

    public void quiz() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();

        FileResource fr = new FileResource( "secretmessage2.txt" );
        String encrypted = fr.asString();

        FileResource dictFr = new FileResource( "dictionaries/English" );

        HashSet<String> dictionary = vb.readDictionary( dictFr );
        int key[] = vb.tryKeyLength( encrypted, 38, 'e' );

        VigenereCipher vc = new VigenereCipher( key );
        String badDecrypt = vc.decrypt( encrypted );
        int wordCount = vb.countWords( badDecrypt, dictionary);
        System.out.println( "Word Count of secretmessage2.txt decrypted with key length 38 is " + wordCount );
    }
}