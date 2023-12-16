package pictures;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Collections;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        // Save user information
        DocumentReference userRef = db.collection(collection).document(document);
        ApiFuture<WriteResult> userResult = userRef.set(data); // Change from userData to data

        // Initialize three empty aquariums
        for (int i = 1; i <= 3; i++) {
            String aquariumId = "aquarium" + i;
            Map<String, Object> aquariumData = Collections.singletonMap("name", "Aquarium " + i);
            DocumentReference aquariumRef = userRef.collection("aquariums").document(aquariumId);
            ApiFuture<WriteResult> aquariumResult = aquariumRef.set(aquariumData);
        }

        System.out.println("User and aquariums saved successfully");
        return true;
        } catch (Exception e) {
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
    
    public static boolean removePerson (String collection, String document) {
        
        db = FirestoreClient.getFirestore();
        
        try {
            DocumentReference docRef = db.collection(collection).document(document);
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            System.out.println("Removed Successfuly");
            return true;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
       
}
