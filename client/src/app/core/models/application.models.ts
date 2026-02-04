export interface Application {
  id: string;
  name: string;
  description?: string;
  createdAt?: string;
  modifiedAt?: string;
}

export interface CreateApplicationRequest {
  name: string;
  description?: string;
}
