/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package astro.moon;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MoonPhase {
    NEW_MOON(            "🌑", "new moon"),
    WAXING_CRESCENT(     "🌒", "waxing crescent"),
    FIRST_QUARTER(       "🌓", "first quarter"),
    WAXING_GIBBOUS(      "🌔", "waxing gibbous"),
    FULL_MOON(           "🌕", "full"),
    WANING_GIBBOUS(      "🌖", "waning gibbous"),
    LAST_QUARTER(        "🌗", "last quarter"),
    WANING_CRESCENT(     "🌘", "waning crescent"),
    NEW_MOON_APPROACHING("🌑", "new moon approaching");

    private final String phase;
    private final String emoji;

    MoonPhase(String emoji, String phase) {
        this.phase = phase;
        this.emoji = emoji;
    }

    public String getPhase() {
        return phase;
    }

    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString() {
        return emoji + " " + phase;
    }
}
