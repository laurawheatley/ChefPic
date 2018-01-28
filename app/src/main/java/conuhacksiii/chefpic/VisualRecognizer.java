package conuhacksiii.chefpic;

/**
 * Created by Derek Yu on 2018-01-27.
 */

import android.content.res.Resources;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class VisualRecognizer {
    private VisualRecognition service;

    public VisualRecognizer() {
        this.service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        this.service.setApiKey(Resources.getSystem().getString(R.string.visual_recognition_api_key));
    }

    public String classifyImage(String filePath) throws FileNotFoundException {
        InputStream imageStream = new FileInputStream(filePath);
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder().imagesFile(imageStream).build();
        ClassifiedImages result = service.classify(classifyOptions).execute();

        List<ClassResult> resultList = result.getImages().get(0).getClassifiers().get(0).getClasses();
        float highestScore = 0;
        String bestMatch = "";
        for (ClassResult c : resultList) {
             if (c.getScore() > highestScore) {
                 highestScore = c.getScore();
                 bestMatch = c.getClassName();
             }
        }

        return bestMatch;
    }
}
