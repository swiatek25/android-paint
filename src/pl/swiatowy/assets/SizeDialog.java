package pl.swiatowy.assets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import pl.swiatowy.R;

/**
 * Created: 2014-06-0314:47
 *
 * @author swiatek25
 */
public abstract class SizeDialog implements IDialog {
    public static final int DEFAULT_SIZE = 25;

    private final Activity activity;
    private final int currentSize;

    public SizeDialog(Activity activity, int currentSize) {
        this.activity = activity;
        this.currentSize = currentSize;
    }

    @Override
    public void show() {
        LayoutInflater inflater = activity.getLayoutInflater();
        View sizeView = inflater.inflate(R.layout.size, null);

        final SeekBar seekBar = (SeekBar) sizeView.findViewById(R.id.seekBar);
        final TextView label = (TextView) sizeView.findViewById(R.id.textView);
        seekBar.setProgress(currentSize);
        label.setText("Size : " + currentSize);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                label.setText("Size: " + (i + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new AlertDialog.Builder(activity)
                .setTitle("Select size")
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onSizeChanged(seekBar.getProgress() + 1);
                    }
                })
                .setView(sizeView).show();
    }

    public abstract void onSizeChanged(int size);
}
