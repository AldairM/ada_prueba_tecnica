export interface Version {
  id: string;
  name: string;
  description?: string;
  createdAt?: string;
  modifiedAt?: string;
}

export interface CreateVersionRequest {
  name: string;
  description?: string;
}
