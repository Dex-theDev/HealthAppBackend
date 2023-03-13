package com.healthapp.project.Entity;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Medication {
  int id();

  String medication_name();

  String strength();
}
