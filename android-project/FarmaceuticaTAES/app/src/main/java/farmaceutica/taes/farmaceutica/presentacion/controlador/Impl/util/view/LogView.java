/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package farmaceutica.taes.farmaceutica.presentacion.controlador.Impl.util.view;

import android.app.Activity;
import android.content.Context;
import android.util.*;
import android.widget.TextView;

/** Simple TextView which is used to output log data received through the LogNode interface.
*/
public class LogView extends TextView {

    public LogView(Context context) {
        super(context);
    }

    public LogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Formats the log data and prints it out to the LogView.
     * @param priority Log level of the data being logged.  Verbose, Error, etc.
     * @param tag Tag for for the log data.  Can be used to organize log statements.
     * @param msg The actual message to be logged. The actual message to be logged.
     * @param tr If an exception was thrown, this can be sent along for the logging facilities
     *           to extract and print useful information.
     */

    /** Takes a string and adds to it, with a separator, if the bit to be added isn't null. Since
     * the logger takes so many arguments that might be null, this method helps cut out some of the
     * agonizing tedium of writing the same 3 lines over and over.
     * @param source StringBuilder containing the text to append to.
     * @param addStr The String to append
     * @param delimiter The String to separate the source and appended strings. A tab or comma,
     *                  for instance.
     * @return The fully concatenated String as a StringBuilder
     */
    private StringBuilder appendIfNotNull(StringBuilder source, String addStr, String delimiter) {
        if (addStr != null) {
            if (addStr.length() == 0) {
                delimiter = "";
            }

            return source.append(addStr).append(delimiter);
        }
        return source;
    }

    // The next LogNode in the chain.

    /** Outputs the string as a new line of log data in the LogView. */
    public void appendToLog(String s) {
        append("\n" + s);
    }


}
