package com.ives.lint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ives on 2017/6/24 0024.
 */

public class ParamLengthRegistry extends IssueRegistry {

    public ParamLengthRegistry() {//!!必须要有无参构造器
    }

    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                WordSpellDetector.ISSUE
        );
    }
}
