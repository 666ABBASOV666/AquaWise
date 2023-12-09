package pictures;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

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



}
