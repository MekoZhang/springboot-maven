package cn.zhangxd.trip.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ThreadSafe
public class ImgPipeline extends FilePersistentBase implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public ImgPipeline() {
        setPath("/data/webmagic/");
    }

    public ImgPipeline(String path) {
        setPath(path);
    }


    @Override
    public void process(ResultItems resultItems, Task task) {
        String fileStorePath = this.path;
        try {

            String imgShortNameNew = "(http://pic.meizitu.com/wp-content/uploads/)|(jpg)";
            CloseableHttpClient httpclient = HttpClients.createDefault();

            for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {

                if (entry.getValue() instanceof List) {

                    List listOne = (List) entry.getValue();
                    List<String> list = new ArrayList<>();

                    for (Object aListOne : listOne) {
                        list.add((String) aListOne);
                    }

                    for (int i = 1; i < list.size(); i++) {

                        StringBuilder sb = new StringBuilder();
                        StringBuilder imgFileNameNewYuan = sb.append(fileStorePath)
                                .append(list.get(0)) //此处提取文件夹名，即之前采集的标题名
                                .append("/");
                        //这里先判断文件夹名是否存在，不存在则建立相应文件夹
                        Path target = Paths.get(imgFileNameNewYuan.toString());
                        if (!Files.isReadable(target)) {
                            Files.createDirectory(target);
                        }

                        String extName = com.google.common.io
                                .Files.getFileExtension(list.get(i));
                        StringBuilder imgFileNameNew = imgFileNameNewYuan
                                .append((list.get(i)).replaceAll(imgShortNameNew, "")
                                        .replaceAll("[\\pP‘’“”]", ""))
                                .append(".")
                                .append(extName);

                        //这里通过httpclient下载之前抓取到的图片网址，并放在对应的文件中
                        HttpGet httpget = new HttpGet(list.get(i));
                        HttpResponse response = httpclient.execute(httpget);
                        HttpEntity entity = response.getEntity();

                        File file = new File(imgFileNameNew.toString());

                        try (InputStream in = entity.getContent()) {
                            FileOutputStream fout = new FileOutputStream(file);
                            int l;
                            byte[] tmp = new byte[1024];
                            while ((l = in.read(tmp)) != -1) {
                                fout.write(tmp, 0, l);
                            }
                            fout.flush();
                            fout.close();
                        }

                    }
                } else {
                    System.out.println(entry.getKey() + ":\t" + entry.getValue());
                }
            }
            httpclient.close();
        } catch (IOException e) {
            logger.warn("write file error", e);
        }
    }
}