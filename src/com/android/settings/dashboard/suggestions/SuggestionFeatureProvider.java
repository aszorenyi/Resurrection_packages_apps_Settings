/*
 * Copyright (C) 2017 The Android Open Source Project
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
 * limitations under the License
 */

package com.android.settings.dashboard.suggestions;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.service.settings.suggestions.Suggestion;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.android.settingslib.drawer.Tile;
import com.android.settingslib.suggestions.SuggestionParser;

import java.util.List;

/** Interface should be implemented if you have added new suggestions */
public interface SuggestionFeatureProvider {

    /**
     * Whether or not the whole suggestion feature is enabled.
     *
     * @deprecated in favor of {@link #isSuggestionV2Enabled(Context)}
     */
    @Deprecated
    boolean isSuggestionEnabled(Context context);

    /**
     * Whether or not the suggestion v2 feature is enabled.
     */
    boolean isSuggestionV2Enabled(Context context);

    /**
     * Returns the component name for SuggestionService.
     */
    ComponentName getSuggestionServiceComponent();

    /**
     * Returns true if smart suggestion should be used instead of xml based SuggestionParser.
     */
    boolean isSmartSuggestionEnabled(Context context);

    /** Return true if the suggestion has already been completed and does not need to be shown */
    boolean isSuggestionComplete(Context context, @NonNull ComponentName suggestion);

    /**
     * Returns the {@link SharedPreferences} that holds metadata for suggestions.
     */
    SharedPreferences getSharedPrefs(Context context);

    /**
     * Ranks the list of suggestions in place.
     *
     * @param suggestions   List of suggestion Tiles
     * @param suggestionIds List of suggestion ids corresponding to the suggestion tiles.
     * @deprecated in favor of SettingsIntelligence
     */
    @Deprecated
    void rankSuggestions(final List<Tile> suggestions, List<String> suggestionIds);

    /**
     * Only keep top few suggestions from exclusive suggestions.
     */
    void filterExclusiveSuggestions(List<Tile> suggestions);

    /**
     * Dismisses a suggestion.
     *
     * @deprecated in favor of {@link #dismissSuggestion(Context, SuggestionControllerMixin,
     * Suggestion)}
     */
    @Deprecated
    void dismissSuggestion(Context context, SuggestionParser parser, Tile suggestion);

    /**
     * Dismisses a suggestion.
     */
    void dismissSuggestion(Context context, SuggestionControllerMixin suggestionMixin,
            Suggestion suggestion);

    /**
     * Returns an identifier for the suggestion
     *
     * @deprecated in favor or {@link Suggestion#getId()}
     */
    @Deprecated
    String getSuggestionIdentifier(Context context, Tile suggestion);

    /**
     * Returns common tagged data for suggestion logging.
     */
    Pair<Integer, Object>[] getLoggingTaggedData(Context context);
}
