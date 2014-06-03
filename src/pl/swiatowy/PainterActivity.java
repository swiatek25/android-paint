package pl.swiatowy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import pl.swiatowy.assets.ColorDialog;
import pl.swiatowy.assets.SizeDialog;

public class PainterActivity extends Activity {

    private PaintView paintView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        paintView = getViewById(R.id.paintview);

        Button colorButton = getViewById(R.id.color);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorDialog(PainterActivity.this) {
                    @Override
                    protected void onColorChange(Integer color) {
                        paintView.changeColor(color);
                    }
                }.show();
            }
        });

        Button sizeButton = getViewById(R.id.size);
        sizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SizeDialog(PainterActivity.this, paintView.getSize()) {
                    @Override
                    public void onSizeChanged(int size) {
                        paintView.changeSize(size);
                    }
                }.show();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private <T> T getViewById(int id) {
        return (T) this.findViewById(id);
    }
}
