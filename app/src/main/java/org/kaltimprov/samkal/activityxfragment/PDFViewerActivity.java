package org.kaltimprov.samkal.activityxfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.kaltimprov.samkal.R;
import org.kaltimprov.samkal.helper.ActivityHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PDFViewerActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    private int pdf_type = 0; //PDF to be viewed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString( R.string.monitoring_samsat ));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(getIntent().getIntExtra("pdf",0) != 0){
            pdf_type = getIntent().getIntExtra("pdf",0);
        }

        ActivityHelper.addFragmentToActivity(getSupportFragmentManager(), new PDFViewerFragment(),
                R.id.pdf_viewer_placeholder);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public int getPdf_type(){
        return this.pdf_type;
    }
}
