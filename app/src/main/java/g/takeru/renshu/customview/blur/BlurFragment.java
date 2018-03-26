package g.takeru.renshu.customview.blur;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import g.takeru.renshu.R;

public class BlurFragment extends Fragment {

    private ImageView mBlurImage;
    private SeekBar mAdjustBar;
    private Bitmap image;


    public BlurFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blur, container, false);
        mBlurImage = view.findViewById(R.id.blur_image_blur);
        mAdjustBar = view.findViewById(R.id.blur_adjustbar);
        mAdjustBar.setMax(24);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBlurImage();
    }

    private void initBlurImage(){
        mBlurImage.setImageResource(R.drawable.img_sunset);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        image = BitmapFactory.decodeResource(getResources(), R.drawable.img_sunset, options);

        mAdjustBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                mBlurImage.setImageBitmap(BlurView.fastBlur(getActivity(), image, progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
