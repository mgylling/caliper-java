/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.validators;

import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;

import static com.google.common.base.Preconditions.checkArgument;

public class TimeValidator {

    /**
     * Constructor
     */
    public TimeValidator() {

    }

    /**
     * Check duration String format
     * @param duration
     * @throws IllegalArgumentException
     */
    public static void checkDuration(String duration) throws IllegalArgumentException {
        if (!(Strings.isNullOrEmpty(duration))) {
            checkArgument(checkPeriodFormat(duration), "%s duration format does not conform to ISO 8601 format P[n]Y[n]M[n]DT[n]H[n]M[n]S or P[n]W");
        }
    }

    /**
     * Check Event end time if Entity requires it.  If an end time is specified check to ensure that
     * the start time precedes the end time.  ValidatorResult.isValid defaults to false.
     * @param start
     * @param end
     * @throws IllegalArgumentException
     */
    protected static void checkEndTime(DateTime start, DateTime end) throws IllegalArgumentException {
        checkArgument(checkDateTime(end), "end time must be specified");
        if (checkDateTime(start)) {
            checkArgument(isBefore(start, end), "end time %s must follow specified start time %s", end, start);
        }
    }

    /**
     * Check Event start time.  If an end time is specified check to ensure that
     * the start time precedes the end time.  ValidatorResult.isValid defaults to false.
     * @param start
     * @param end
     * @throws IllegalArgumentException
     */
    protected static void checkStartTime(DateTime start, DateTime end) throws IllegalArgumentException {
        // checkArgument(checkDateTime(start), "start time must be specified"); // SHOULD not MUST
        if (checkDateTime(end)) {
            checkArgument(isBefore(start, end), "start time %s must precede specified end time %s", start, end);
        }
    }

    /**
     * Check that the time value is not null.
     * @param time
     * @return boolean true/false.
     */
    private static boolean checkDateTime(DateTime time) {
        return time != null;
    }

    /**
     * Parses duration string against the standard ISO8601 duration format: PyYmMwWdDThHmMsS.
     * Note that milliseconds precision is not defined as part of the standard duration format.
     * @param period
     * @return boolean true/false
     */
    private static boolean checkPeriodFormat(String period) {
        try {
            Period.parse(period, ISOPeriodFormat.standard());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Event start times are required; Event end times are optional while Entity start and end times are both
     * typically optional.  However if both start and end times are specified check that the start time
     * precedes the end time.  The isBefore() method evaluates null instants to check against as now.
     * @param start
     * @param end
     * @return boolean true/false
     */
    private static boolean isBefore(DateTime start, DateTime end) {
        return start.isBefore(end);
    }
}