package pictures;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    
    public static boolean savePerson(String collection, String document, Map<String, Object> data) {
        Firestore db = FirestoreClient.getFirestore();

        try {
            // Reference to the user document
            DocumentReference userRef = db.collection(collection).document(document);

            // Check if the user document already exists
            DocumentSnapshot userSnapshot = userRef.get().get();
            if (userSnapshot.exists()) {
                // Update the existing user document
                ApiFuture<WriteResult> userResult = userRef.update(data);
            } else {
                // Save user information with the UUID as the document name
                ApiFuture<WriteResult> userResult = userRef.set(data);

                // Create three aquarium documents for the user
                for (int i = 1; i <= 3; i++) {
                    String aquariumId = "aquarium" + i;
                    Map<String, Object> aquariumData = Collections.singletonMap("name", "Aquarium " + i);
                    DocumentReference aquariumRef = userRef.collection("aquariums").document(aquariumId);
                    ApiFuture<WriteResult> aquariumResult = aquariumRef.set(aquariumData);
                }
            }

            System.out.println("User and aquariums saved successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
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
    
    public static boolean saveFishToAquarium(String userEmail, String aquariumName, String fishName) {
        try {
            Firestore db = FirestoreClient.getFirestore();

            DocumentReference userRef = db.collection("Person").document(userEmail);
            DocumentSnapshot userSnapshot = userRef.get().get();

            if (userSnapshot.exists()) {
                // User document exists, proceed to aquarium
                DocumentReference aquariumRef = userSnapshot.getReference().collection("aquariums").document(aquariumName);

                // Check if the aquarium document already exists
                DocumentSnapshot aquariumSnapshot = aquariumRef.get().get();
                if (aquariumSnapshot.exists()) {
                    // Prepare the data for the fish to be stored in the "fishData" field
                    Map<String, Object> fishData = Collections.singletonMap("fishData", fishName);

                    // Update the "fishData" field with the new fish information
                    ApiFuture<WriteResult> result = aquariumRef.update("fishData", FieldValue.arrayUnion(fishName));

                    System.out.println("Fish saved successfully");
                    return true;
                } else {
                    System.out.println("Aquarium document does not exist for " + userEmail + " / " + aquariumName);
                }
            } else {
                System.out.println("User document not found for email: " + userEmail);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }





    
    public static boolean saveAquarium(String userEmail, String aquariumId, Map<String, Object> aquariumData) {
        try {
            Firestore db = FirestoreClient.getFirestore();

            DocumentReference userRef = db.collection("Person").document(userEmail);
            DocumentReference aquariumRef = userRef.collection("aquariums").document(aquariumId);

            ApiFuture<WriteResult> aquariumResult = aquariumRef.set(aquariumData);

            System.out.println("Aquarium saved successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
       
}
