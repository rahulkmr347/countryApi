package com.student.studentDetails.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppConstants {
    private AppConstants() {
    }

    public static final List<String> keys = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("cca2");
                add("ccn3");
                add("cca3");
                add("cioc");
                add("independent");
                add("status");
                add("unMember");
                add("region");
                add("subregion");
                add("landlocked");
            }});
}
