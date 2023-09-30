package dev.kambaabi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConventionalCommitsRegexValidator implements CommitTextValidator {


    /**
     * Found this at https://gist.github.com/marcojahn/482410b728c31b221b70ea6d2c433f0c.
     */
    private final String regexOld = "^(build|chore|ci|docs|feat|fix|perf|refactor|revert|style|test){1}(\\([\\w\\-\\.]+\\))?(!)?: ([\\w ])+([\\s\\S]*)";

    /**
     * Improved version:
     * for a given test like this:
     * """
     * feat(SCOPE)!: ornek deneme hiiiammmk
     * <p>
     * seni Allah bildiği gibi yapsın
     * """
     * it validates and returns these capture groups:
     * Group 1: feat
     * Group 2: (SCOPE)
     * Group 3: !
     * Group 4: ornek deneme hiiiammmk
     * Group 5: \n\n seni Allah bildiği gibi yapsın
     */
    private final String regex = "^(build|chore|ci|docs|feat|fix|perf|refactor|revert|style|test){1}(\\([\\w\\-\\.]+\\))?(!)?: ([\\w ]+)(\\n[\\s\\S]*)?";

    /**
     * This one matches 5 groups which can be used individually for extensive checking.
     * Matcher Group 1 is: [feat]
     * Matcher Group 2 is: [SCOPE]
     * Matcher Group 3 is: [!]
     * Matcher Group 4 is: [ornek deneme]
     * Matcher Group 5 is: [\nseni Allah bildiği gibi yapsın\n\m]
     */
    private final String regex2 = "^(\\w*)(?:\\(([\\w\\-.]+)\\))?(!)?: ([\\w ]+)(\\n[\\s\\S]*)?";

    private final Pattern pattern = Pattern.compile(regex2);

    public ConventionalCommitsRegexValidator(String... args) {
    }


    @Override
    public ConventionalCommitsRegexValidator createInstance(String... args) {
        return new ConventionalCommitsRegexValidator(args);
    }

    @Override
    public boolean validate(String value) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
