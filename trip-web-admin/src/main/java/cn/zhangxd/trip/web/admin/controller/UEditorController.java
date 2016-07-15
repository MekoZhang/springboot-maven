package cn.zhangxd.trip.web.admin.controller;

import cn.zhangxd.trip.web.admin.utils.upload.FileIndex;
import cn.zhangxd.trip.web.admin.utils.upload.FileManager;
import com.baidu.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/ueditor")
public class UEditorController {
 
    @RequestMapping(value="")
    public void config(HttpServletRequest request, HttpServletResponse response) {
 
        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");
 
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

    @Autowired
    private FileManager fileManager;

    @RequestMapping(value="/uploadimage")
    @ResponseBody
    public UploaderParam uploadimage(@RequestParam(value = "upfile", required = false) MultipartFile upfile) {

        UploaderParam data = new UploaderParam();

        if (upfile != null && upfile.getSize() > 0) {
            FileIndex ufi = new FileIndex();
            ufi.setmUpfile(upfile);
            ufi.setTruename(upfile.getOriginalFilename());
            ufi.setMcode("ueditor");
            ufi = fileManager.save(ufi);

            String path = ufi.getPath();

            data.setName(upfile.getName());
            data.setOriginalName(upfile.getOriginalFilename());
            data.setSize(upfile.getSize());
            data.setState("SUCCESS");
            data.setType(upfile.getContentType());
            data.setUrl(fileManager.getFileUrlByRealpath(path));

        }

        return data;
    }

    private class UploaderParam {

        private String name; //通过upfile获取
        private String originalName; //通过upfile获取
        private Long size; //通过upfile获取
        private String state;//成功必须为"SUCCESS"
        private String type; //通过upfile获取
        private String url; //图片回显url

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}