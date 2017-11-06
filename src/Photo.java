import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Photo {

    private final int id;
    private final String filename;
    private final long takenAt;

    private Photo(int id, String filename, long takenAt) {
        this.id = id;
        this.filename = filename;
        this.takenAt = takenAt;
    }

    private int getId() {
        return id;
    }

    private String getFilename() {
        return filename;
    }

    private long getTakenAt() {
        return takenAt;
    }

    public static void main(String[] args) throws ParseException {
         lookForPhotos(inputAndValidateDate(), initialisePhotosList());
    }

    private static void lookForPhotos(String searchDate, ArrayList<Photo> lsPhotos){
        if (searchDate != null) {
            Photo[] photo = findPhotos(lsPhotos, searchDate);
            System.out.println("Found photos:");
            for (Photo exPhoto : photo) {
                System.out.println(String.format("%s  %s  %s", exPhoto.getId(), exPhoto.getFilename(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exPhoto.getTakenAt())));
            }
        }
    }

    private static ArrayList<Photo> initialisePhotosList() throws ParseException {
        ArrayList<Photo> lsPhotos = new ArrayList<>();
        final int INITIAL_PHOTOS_SIZE = 9999;

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date start = dateFormat.parse("2016.02.01 00:00:00");
        Date end = dateFormat.parse("2017.12.31 00:00:00");

        for (int i = 0; i < INITIAL_PHOTOS_SIZE; i++) {
            long date = start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime()));
            lsPhotos.add(new Photo(i, String.format("IMG_%04d_JPG", i), date));
        }
        return lsPhotos;
    }

    private static String inputAndValidateDate(){
        for(int i=0; i<10; i++){
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the date - (yyyy-MM-dd): ");
            String searchDate = in.nextLine();
            if (isDateValid(searchDate)) {
                  return searchDate;
                }
            else {System.out.println("Date is wrong");}
        }
        return null;
    }

    private static boolean isDateValid(String value) {
        if (value == null) {
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        formatter.setLenient(false);
        try {
            formatter.parse(value);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private static Photo[] findPhotos(ArrayList<Photo> photos, String date) {
        Photo[] photosList;
        List<Photo> pList = new ArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Photo photo;

        for(int i=0; i<photos.size(); i++) {
            photo = photos.get(i);
            if (sdf.format(photo.getTakenAt()).contains(date)){
                pList.add(photo);
            }
        }

        photosList  = pList.toArray(new Photo[pList.size()]);
        return photosList;
    }
}
