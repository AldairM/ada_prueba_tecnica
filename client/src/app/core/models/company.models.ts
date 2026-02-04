export interface Company {
  id: string;
  name: string;
  description?: string;
  createdAt?: string;
  modifiedAt?: string;
}

export interface CreateCompanyRequest {
  name: string;
  description?: string;
}
