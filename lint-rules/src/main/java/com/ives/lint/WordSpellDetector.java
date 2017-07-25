package com.ives.lint;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.Node;
import lombok.ast.VariableDeclaration;

/**
 * 名词拼写检查器
 * Created by Ives on 2017/7/9 0009.
 */

public class WordSpellDetector extends Detector implements Detector.JavaPsiScanner, Detector.JavaScanner {

    /**
     * 这是一个Issue声明
     */
    public final static Issue ISSUE = Issue.create("WordSpellStaticFinalUpper",
            "Static final field should be all uppercase",
            "Static final field should be all uppercase as our specification",
            Category.CORRECTNESS,
            9,
            Severity.WARNING,
            new Implementation(WordSpellDetector.class,
                    Scope.JAVA_FILE_SCOPE));

    @Override
    public List<Class<? extends PsiElement>> getApplicablePsiTypes() {
        return Arrays.asList(PsiField.class);//声明为检查成员变量类型节点
    }

    @Override
    public JavaElementVisitor createPsiVisitor(JavaContext context) {
        return new WordSpellChecker(context);//返回本检查器真正的检查执行者
    }

    private static class WordSpellChecker extends JavaElementVisitor {
        private JavaContext mContext;
        WordSpellChecker(JavaContext context){
            this.mContext = context;
        }

        @Override
        public void visitField(PsiField field) {
            if(field==null)return;
            PsiElement[] children = field.getChildren();
            if(children!=null){
                for (int i = 0; i < children.length; i++) {
                    if(!isAllUpper(field.getName())
                            && children[i].getText().contains("final") &&children[i].getText().contains("static")){
                        mContext.report(ISSUE, mContext.getLocation(field), "field name with static final should be all uppercase");
                    }
                }
            }
            super.visitField(field);
        }
    }
    private static boolean isAllUpper(String str){
        if(str==null)return true;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]>='a'&& chars[i]<='z')return false;//只判断有没有小写字母，不限定其它特殊字符
        }
        return true;
    }
}
