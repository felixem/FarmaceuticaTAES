package farmaceutica.taes.farmaceutica.Utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by felix on 15/05/15.
 */

public class ImageConverter {
    public static Bitmap ImageFromBuffer(byte[] bitmapdata)
    {
       return BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
    }

    public static byte[] BufferFromImage(Bitmap bitmap)
    {
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, blob);
        return blob.toByteArray();
    }
}
