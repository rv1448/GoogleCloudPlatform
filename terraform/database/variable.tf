variable "tier" {default = "db-f1-micro"}
variable "name" {default = "gcpdatabase"}
variable "db_region" {default="us-central1"}
variable "disk_size" {
    default="20"
}

variable "database_version" {
  default="MYSQL_5_7"
}

variable "user_host" {
  default="%"
}

variable "user_name" {
  default="admin"
}
variable "password" {
  default="admin12345"
}

variable "replication_type" {
  default="SYNCHRONOUS"
}

variable "activation_policy" {
  default="always"
}

variable "project_id" {
    type = string
    default = "playground-s-11-fd286164"
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