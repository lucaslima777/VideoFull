package video.full.lln.play;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import video.full.lln.R;
import video.full.lln.controller.FensterPlayerController;
import video.full.lln.controller.FensterPlayerControllerVisibilityListener;
import video.full.lln.controller.SimpleMediaFensterPlayerController;
import video.full.lln.view.FensterLoadingView;
import video.full.lln.view.FensterVideoView;

public class FensterVideoFragment extends Fragment implements FensterVideoStateListener {

    private View contentView;
    private FensterVideoView textureView;
    private FensterPlayerController fensterPlayerController;
    private FensterLoadingView fensterLoadingView;

    public FensterVideoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fen__fragment_fenster_video, container);
        textureView = (FensterVideoView) contentView.findViewById(R.id.fen__play_video_texture);
        fensterPlayerController = (FensterPlayerController) contentView.findViewById(R.id.fen__play_video_controller);
        fensterLoadingView = (FensterLoadingView) contentView.findViewById(R.id.fen__play_video_loading);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVideo();
    }

    private void initVideo() {
        textureView.setMediaController(fensterPlayerController);
        textureView.setOnPlayStateListener(this);
    }

    public void playExampleVideo() {
        textureView.setVideo("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
                SimpleMediaFensterPlayerController.DEFAULT_VIDEO_START);
        textureView.start();
    }

    public void setVisibilityListener(FensterPlayerControllerVisibilityListener visibilityListener) {
        fensterPlayerController.setVisibilityListener(visibilityListener);
    }

    public void showFensterController() {
        fensterLoadingView.hide();
        fensterPlayerController.show();
    }

    private void showLoadingView() {
        fensterLoadingView.show();
        fensterPlayerController.hide();
    }

    @Override
    public void onFirstVideoFrameRendered() {
        fensterPlayerController.show();
    }

    @Override
    public void onPlay() {
        showFensterController();
    }

    @Override
    public void onBuffer() {
        showLoadingView();
    }

    @Override
    public boolean onStopWithExternalError(int position) {
        return false;
    }
}
