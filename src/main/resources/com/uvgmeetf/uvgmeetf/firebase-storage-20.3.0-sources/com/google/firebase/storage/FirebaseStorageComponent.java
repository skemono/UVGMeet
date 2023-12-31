// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.firebase.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

class FirebaseStorageComponent {
  /** A map from storage buckets to Firebase Storage instances. */
  private final Map<String, FirebaseStorage> instances = new HashMap<>();

  private final FirebaseApp app;
  @Nullable private final Provider<InternalAuthProvider> authProvider;
  @Nullable private final Provider<InteropAppCheckTokenProvider> appCheckProvider;

  FirebaseStorageComponent(
      @NonNull FirebaseApp app,
      @Nullable Provider<InternalAuthProvider> authProvider,
      @Nullable Provider<InteropAppCheckTokenProvider> appCheckProvider,
      @NonNull @Blocking Executor blockingExecutor,
      @NonNull @UiThread Executor uiExecutor) {
    this.app = app;
    this.authProvider = authProvider;
    this.appCheckProvider = appCheckProvider;
    StorageTaskScheduler.initializeExecutors(blockingExecutor, uiExecutor);
  }

  /** Provides instances of Firebase Storage for given bucket names. */
  @NonNull
  synchronized FirebaseStorage get(@Nullable String bucketName) {
    FirebaseStorage storage = instances.get(bucketName);
    if (storage == null) {
      storage = new FirebaseStorage(bucketName, app, authProvider, appCheckProvider);
      instances.put(bucketName, storage);
    }
    return storage;
  }

  @VisibleForTesting
  synchronized void clearInstancesForTesting() {
    instances.clear();
  }
}
