package com.healthapp.project.Entity;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Medication {
  //int id();

  String brand_name();

  String strength();

  String dosage_form();
}
