package com.dongtech.mygit2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GitConfig {
    @Value("${GitRepository.Url}")
    public String GitUrl;
    @Value("${GitRepository.BranchName}")
    public String GitBranchName;
    @Value("${GitRepository.ProjectName}")
    public String GitProjectName;
    @Value("${GitRepository.UserName}")
    public String GitUserName;
    @Value("${GitRepository.Password}")
    public String GitPassword;
    @Value("${GitRepository.LocalRootPath}")
    public String LocalRootPath;
}
