variable "name_count" {
  default = ["server-1","server-2","server-3"]
}


variable "image" {
  default = "ubuntu-os-cloud/ubuntu-1804-lts"
}   
variable "machine_type" {

    type=map(string)
    default = {
        "dev":"n1-standard-1"
        "prod":"production-box"
    }
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