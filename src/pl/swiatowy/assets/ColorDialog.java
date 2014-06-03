package pl.swiatowy.assets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;


/**
 * Created: 2014-06-0314:41
 *
 * @author swiatek25
 */
public abstract class ColorDialog implements IDialog {
    private static final Map<String, Integer> AVAILABLE_COLORS = new HashMap<>();
    public static final int DEFAULT_COLOR = Color.BLUE;

    static {
        AVAILABLE_COLORS.put("Red", Color.RED);
        AVAILABLE_COLORS.put("Green", Color.GREEN);
        AVAILABLE_COLORS.put("Blue", DEFAULT_COLOR);
    }

    private final Activity activity;

    public ColorDialog(Activity activity) {
        this.activity = activity;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Pick color")
                .setItems(AVAILABLE_COLORS.keySet().toArray(new String[AVAILABLE_COLORS.size()]), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                onColorChange(AVAILABLE_COLORS.get(AVAILABLE_COLORS.keySet().toArray()[which]));
                            }
                        }
                );
        builder.create().show();
    }

    protected abstract void onColorChange(Integer color);
}
