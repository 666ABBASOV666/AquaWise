package pictures;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class personProvider {
    
    CollectionReference reference;
    
    static Firestore db;
    
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
    
    public static boolean editPerson (String collection, String document, Map <String, Object> data) {
        
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = db.collection(collection).document(document);
            ApiFuture<WriteResult> result = docRef.update(data);
            System.out.println("Edited Successfuly");
            return true;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
       
}
