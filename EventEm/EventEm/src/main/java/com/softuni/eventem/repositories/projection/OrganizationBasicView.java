package com.softuni.eventem.repositories.projection;

import java.util.UUID;

public interface OrganizationBasicView {
  UUID getId();
  String getName();
  String getCity();
  String getAddress();

}
