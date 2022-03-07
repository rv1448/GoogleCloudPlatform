variable "project_id" {
    type = string
    default = "playground-s-11-4feb0123"
}

variable "cred_json" {
    type = string
    default = "/Users/rahulvangala/GoogleCloudPlatform/terraform/keys/secrets.json"
}

variable "region" {
    type = string
    default = "us-central1"
}


variable "zone" {
    type = string
    default = "us-central1-c"
}


variable "bucket_name" {
  type=string
}
variable "folder_path" {
  type=string
}