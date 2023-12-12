package pictures;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class DatabaseHandler {
    // Reference to the 'Person' collection in Firestore
    private static CollectionReference personCollection;

    static {
        // Ensure that ConnectFirebase is called before any operations
        ConnectionTrue.ConnectFirebase();

        // Initialize the 'Person' collection reference
        personCollection = ConnectionTrue.db.collection("Person");
    }

   public static boolean checkLogin(String email, String password) {
        try {
            // Query Firestore to check if the provided email and password match a person
            ApiFuture<QuerySnapshot> querySnapshot = personCollection.whereEqualTo("Email", email).get();

            if (!querySnapshot.get().isEmpty()) {
                DocumentSnapshot personSnapshot = querySnapshot.get().getDocuments().get(0);

                // Person with the provided email exists
                String storedPassword = personSnapshot.getString("Password");

                // Check if the stored password matches the provided password
                return storedPassword != null && storedPassword.equals(password);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace
        }

        return false;
    }
   
    public static boolean removePerson(String email) {
        try {
            // Check if the provided email exists
            ApiFuture<QuerySnapshot> querySnapshot = personCollection.whereEqualTo("Email", email).get();

            if (!querySnapshot.get().isEmpty()) {
                // Email exists, proceed with removal
                DocumentReference docRef = personCollection.document(querySnapshot.get().getDocuments().get(0).getId());
                ApiFuture<WriteResult> result = docRef.delete();  // Removed Precondition.NONE
                System.out.println("Removed Successfully");
                return true;
            } else {
                System.out.println("Email not found in the collection.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
    //this method used in login class for gathering the name of the user and send it to the main class
    public static String getUserName(String email) {
    try {
        ApiFuture<QuerySnapshot> querySnapshot = personCollection.whereEqualTo("Email", email).get();

        if (!querySnapshot.get().isEmpty()) {
            DocumentSnapshot personSnapshot = querySnapshot.get().getDocuments().get(0);
            return personSnapshot.getString("Name");
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print the exception stack trace
    }

    return null;
    }
    
    public static String getPersonIdByEmail(String email) {
    try {
        ApiFuture<QuerySnapshot> querySnapshot = personCollection.whereEqualTo("Email", email).get();

        if (!querySnapshot.get().isEmpty()) {
            DocumentSnapshot personSnapshot = querySnapshot.get().getDocuments().get(0);
            return personSnapshot.getId();
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print the exception stack trace
    }

    return null;
}

   
   



}
