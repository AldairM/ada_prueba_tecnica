export interface VersionCompany {
  id: string;
  name: string;
  description?: string;
  createdAt?: string;
  modifiedAt?: string;
}

export interface CreateVersionCompanyRequest {
  name: string;
  description?: string;
}
