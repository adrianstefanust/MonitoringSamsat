package org.kaltimprov.samkal.activityxfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.kaltimprov.samkal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PDFViewerFragment extends Fragment {


    @BindView(R.id.webview_pdf)
    WebView webviewPdf;
    Unbinder unbinder;
    @BindView(R.id.progress_load)
    ProgressBar progressLoad;
    @BindView(R.id.text_wait)
    TextView textWait;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_pdf_viewer, container, false );
        unbinder = ButterKnife.bind( this, view );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        webviewPdf.getSettings().setJavaScriptEnabled( true );
        String pdf_url = "http://proxy.bpprdku.net/hasil/tespdf.pdf";
        webviewPdf.setWebViewClient( new WebViewClient () {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished( view, url );
                progressLoad.setVisibility( View.INVISIBLE );
                textWait.setVisibility( View.INVISIBLE );
            }
        } );
        webviewPdf.loadUrl( "http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf_url );
        super.onViewCreated( view, savedInstanceState );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webviewPdf.destroy();
        unbinder.unbind();
    }
}
