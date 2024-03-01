package be.uclouvain.lsinf1225.groupel32.wishlist.Backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageToBlob {
        // convert bitmap to byte[]
        public static byte[] getBytes(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }

        // convert byte[] to bitmap
        public static Bitmap getBytePhoto(byte[] image) {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }

        // Convert Uri to byte[]
        public static byte[] getBytes(Uri image, Context context) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), image);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
                return stream.toByteArray();
            }catch (IOException e){
                Toast.makeText(context, "ERROR WHEN GET FILE", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
}

