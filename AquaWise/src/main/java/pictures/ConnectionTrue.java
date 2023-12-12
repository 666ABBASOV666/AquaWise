package pictures;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static pictures.personProvider.db;

/**
 * This class handles the connection to Firebase Firestore.
 */
public class ConnectionTrue {

    public static com.google.cloud.firestore.Firestore db;
    

    /**
     * Connects to Firebase Firestore.
     */
    public static void ConnectFirebase() {
        try {
            FileInputStream sa = new FileInputStream("authfirebaseaquawise.json");
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(sa))
                .build();

            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            FirebaseApp firebaseApp = null;

            for (FirebaseApp app : firebaseApps) {
                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                    firebaseApp = app;
                    break;
                }
            }

            if (firebaseApp == null) {
                FirebaseApp.initializeApp(options);
            }

            db = FirestoreClient.getFirestore();
            System.out.println("Success!!! ");
        } catch (IOException e) {
            System.err.println("Error reading the authentication file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error establishing connection to Firestore: " + e.getMessage());
        }
    }
    
    public static boolean savePerson (String collection, String document, Map <String, Object> data) {
        
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = db.collection(collection).document(document);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Saved Successfuly");
            return true;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
    public static void updatePerson(String collection, String email, Map<String, Object> newData) {
        
        db = FirestoreClient.getFirestore();
        
        try {
            
            DocumentReference docRef = db.collection(collection).document(email);

            // Update the document with the new data
            ApiFuture<WriteResult> result = docRef.update(newData);

            System.out.println("Updated Successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
