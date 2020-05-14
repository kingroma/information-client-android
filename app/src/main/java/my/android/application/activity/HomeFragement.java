package my.android.application.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import api.data.ContentData;
import api.data.ContentProgramData;
import api.data.ContentProgramImageMetaData;
import api.service.ContentService;
import my.android.application.activity.R;

public class HomeFragement extends Fragment {
	
	private ContentService contentService = null ;
	
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentService = new ContentService();
        View view = inflater.inflate(R.layout.activity_main, container, false);



        return view ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void getAllConents() {
    	List<ContentData> cdList = contentService.getContentAll();

    	if ( cdList != null && cdList.size() > 0 ) {
    	    for ( ContentData cd : cdList ) {
    	        String contentId = cd.getContentId();
    	        String contentName = cd.getContentName();
    	        ContentData.ContentType contentType = cd.getContentType();

    	        List<ContentProgramData> cpdList = cd.getList();

    	        if ( cpdList != null && cpdList.size() > 0 ) {
                    for (ContentProgramData cpd : cpdList) {
                        String programId = cpd.getProgramId();
                        String title = cpd.getTitle();

                        List<ContentProgramImageMetaData> cpimdList = cpd.getImageMeta();

                        if ( cpimdList != null && cpimdList.size() > 0 ) {
                            for ( ContentProgramImageMetaData cpimd : cpimdList ) {
                                String imageId = cpimd.getImageId();
                                String imageType = cpimd.getImageType();
                                String mappingId = cpimd.getMappingId();
                            }
                        }
                    }
                }
            }
        }
    }
}