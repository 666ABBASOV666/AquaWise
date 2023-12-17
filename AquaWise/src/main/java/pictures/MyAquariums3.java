/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pictures;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class MyAquariums3 extends javax.swing.JFrame {
    String[] fishes = new String[]{"Alligator Gar","Amberjack","Arapaima","Arctic Char","Asp","Barracuda","Barramundi","Bass","Black Drum",
    "Black Jewfish","Black Rockfish","Bluefish","Bluenose Warehou","Bohar Snapper","Bonefish","Bonito","Bowfin","Bream","Brook Trout",
    "Brown Trout","Bullhead","Burbot","Calico Bass","California Corbina",
    "California Sheephead","Carp","Catfish","Cero Mackerel","Clam","Clown Knife Fish","Coalfish","Cobia","Cod","Common Ling",
    "Common Pandora","Conger Eel","Coral Trout","Crab","Crappie","Crayfish","Cutthroat Trout","Dentex","Dhufish","Dogfish","Dolly Varden","Flathead",
    "Flounder","Freshwater Drum","Garfish","Geelbek"};

    ArrayList<String> aquariumList = new ArrayList<>();
    private String aquariumName;
    private String userEmail;
    int[] maxArray = {
        29, 26, 29, 29, 29, 27, 29, 27, 28, 28, 29, 29, 30, 25, 25, 22, 21, 24, 23, 21, 23, 20, 20, 29, 22, 22, 21, 21, 22, 23, 
        25, 23, 24, 22, 24, 23, 26, 21, 29, 23, 24, 26, 22, 22, 26, 27, 26, 23, 22, 26};

    int[] minArray = {
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 
        21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21};
    boolean isFit;
    String chosenFish = "";
    
    public MyAquariums3(String aquariumName, String userEmail) {
        
        
        initComponents();
        setLocationRelativeTo(null);
        this.aquariumName = aquariumName;
        this.userEmail = userEmail;

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String fish : fishes) {
            listModel.addElement(fish);
        }

        JList<String> fishList = new JList<>(listModel);
        fishList.addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting()) {
                handleFishSelection(fishList.getSelectedValue());
                chosenFish = fishList.getSelectedValue();
            }
        });

        jScrollPane1.setViewportView(fishList);
        jScrollPane1.setPreferredSize(new Dimension(180, 70));
    }

    private void handleFishSelection(String selectedFish) {
        if (aquariumList.isEmpty()) {
            isFit = true;
            showFishAdditionMessage();
        } else {
            int selectedFishIndex = -1;
            for (int i = 0; i < fishes.length; i++) {
                if (fishes[i].equals(selectedFish)) {
                    selectedFishIndex = i;
                    break;
                }
            }
    
            boolean canAddFish = true;
            for (int i = 0; i < aquariumList.size(); i++) {
                int aquariumFishIndex = -1;
                for (int j = 0; j < fishes.length; j++) {
                    if (fishes[j].equals(aquariumList.get(i))) {
                        aquariumFishIndex = j;
                        break;
                    }
                }
    
                if (selectedFishIndex != -1 && aquariumFishIndex != -1 && !((minArray[selectedFishIndex] <= maxArray[aquariumFishIndex]
                        && minArray[selectedFishIndex] >= minArray[aquariumFishIndex])
                        || (maxArray[selectedFishIndex] >= minArray[aquariumFishIndex]) && maxArray[selectedFishIndex] <= maxArray[aquariumFishIndex])) {
                    canAddFish = false;
                    break;
                }
            }
    
            if (canAddFish) {
                isFit = true;
            } else {
                isFit = false;
            }
    
            showFishAdditionMessage();
        }
    }
    
   private void showFishAdditionMessage() {
    String message;
    if (isFit) {
        message = "This fish can be added to the aquarium.";
    } else {
        message = "This fish cannot be added to the aquarium.";
    }

    // Create a non-modal dialog
    JDialog dialog = new JDialog(this, "Fish Addition Message", true);
    dialog.setLayout(new BorderLayout());
    dialog.setSize(300, 100);
    dialog.setLocationRelativeTo(this);

    // Add a label with the message to the dialog
    JLabel label = new JLabel(message, SwingConstants.CENTER);
    dialog.add(label, BorderLayout.CENTER);

    // Set a timer to close the dialog after a short delay
    Timer timer = new Timer(1000, (ActionEvent e) -> {
        dialog.setVisible(false);
        dialog.dispose();
    });
    timer.setRepeats(false);
    timer.start();

    // Show the dialog
    dialog.setVisible(true);
}


    private void addToAquarium(String fish) {
       aquariumList.add(fish);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackgroundPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        WaterEditTab = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        FishCountEditTab = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        WaterEditTF = new javax.swing.JTextField();
        FishTypeTab = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BackgroundPanel.setBackground(new java.awt.Color(102, 141, 209));

        jPanel1.setPreferredSize(new java.awt.Dimension(130, 35));

        jLabel1.setText("Edit Aquarium");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setText("Water:");

        javax.swing.GroupLayout WaterEditTabLayout = new javax.swing.GroupLayout(WaterEditTab);
        WaterEditTab.setLayout(WaterEditTabLayout);
        WaterEditTabLayout.setHorizontalGroup(
            WaterEditTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WaterEditTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );
        WaterEditTabLayout.setVerticalGroup(
            WaterEditTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jLabel4.setText("Fish Count:");

        javax.swing.GroupLayout FishCountEditTabLayout = new javax.swing.GroupLayout(FishCountEditTab);
        FishCountEditTab.setLayout(FishCountEditTabLayout);
        FishCountEditTabLayout.setHorizontalGroup(
            FishCountEditTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FishCountEditTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );
        FishCountEditTabLayout.setVerticalGroup(
            FishCountEditTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jLabel5.setText("Fish Type:");

        javax.swing.GroupLayout FishTypeTabLayout = new javax.swing.GroupLayout(FishTypeTab);
        FishTypeTab.setLayout(FishTypeTabLayout);
        FishTypeTabLayout.setHorizontalGroup(
            FishTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FishTypeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        FishTypeTabLayout.setVerticalGroup(
            FishTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BackgroundPanelLayout = new javax.swing.GroupLayout(BackgroundPanel);
        BackgroundPanel.setLayout(BackgroundPanelLayout);
        BackgroundPanelLayout.setHorizontalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundPanelLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BackgroundPanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(WaterEditTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FishCountEditTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FishTypeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(WaterEditTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(BackgroundPanelLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        BackgroundPanelLayout.setVerticalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(WaterEditTF)
                    .addComponent(WaterEditTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FishCountEditTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FishTypeTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (isFit) {
            addToAquarium(chosenFish);
            JOptionPane.showMessageDialog(this, "Fish added to the aquarium!");
            System.out.println(chosenFish);

            // Get the selected fish and aquarium name
            String selectedFish = chosenFish;

            // Save the selected fish to the aquarium in the database
            personProvider.saveFishToAquarium(userEmail, aquariumName, selectedFish);
        } else {
            JOptionPane.showMessageDialog(this, "You cannot add this fish because it doesn't suit with others.");
        }

        // Close the MyAquariums3 popup or perform any other actions needed
        //close();
    }//GEN-LAST:event_saveButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JPanel FishCountEditTab;
    private javax.swing.JPanel FishTypeTab;
    private javax.swing.JTextField WaterEditTF;
    private javax.swing.JPanel WaterEditTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    private void close() {
        dispose();
    }
}
