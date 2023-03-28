public class ImageEncryption {

    private byte[] encryptedDataImages;
    private String patientID;



    public String getPatientID() {
        return patientID;
    }
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public byte[] getEncryptedDataImages() {
        return encryptedDataImages;
    }
    public void setEncryptedDataImages(byte[] encryptedImageData) {
        this.encryptedDataImages = encryptedImageData;
    }
}
