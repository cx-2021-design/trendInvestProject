package org.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RefreshScope//允许刷新
public class ViewController {
    @Value("${version}")//获取版本信息
    String version;
    @GetMapping("/")
    public String view(Model m) throws Exception {
        m.addAttribute("version", version);
        return "view";
    }
}
