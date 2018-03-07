<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Mobileapiservice extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		$this->load->model('MobileapiserviceModel');
	}

	public function index()
	{
		
	}

	public function submitService()
	{
		$submitted_data = $this->input->post();
		$id_user = $submitted_data['id_user'];
		$service_name = $submitted_data['service_name'];
		$description = $submitted_data['description'];

		if($id_user == "" || $service_name == ""){
			exit();
		}else{
			if($this->MobileapiserviceModel->submitService($submitted_data)){
				$id_service_req = $insert_id = $this->db->insert_id();
				echo json_encode(array("success"=>"true", "id_service_req"=>$id_service_req));
			}
		}

	}

	public function uploadServiceImage()
	{
		$image = $this->input->post('image', TRUE);
		$id_service_req = $this->input->post('id_service_req', TRUE);


		$now = DateTime::createFromFormat('U.u', microtime(true));
		$id = $now->format('YmdHisu');

		$upload_folder = "images/service_images";
		$path = "$upload_folder/$id.jpeg";

		$image_name = $id.".jpeg";

		$this->MobileapiserviceModel->uploadServiceImage($id_service_req,$image_name);

		if(file_put_contents($path, base64_decode($image)) != false){
			echo "success";
		}
		else{
			echo "fail";
		}
	}

	public function getTechnicians()
	{	
		$technicians = $this->MobileapiserviceModel->getTechnicians();
		echo json_encode(array("technicians" => $technicians, "success" => "true"));
	}

	public function searchTechnician()
	{
		$name_or_area = trim($this->input->get('name_or_area', TRUE));
		if($name_or_area == ""){
			exit();
		}
		$technicians = $this->MobileapiserviceModel->searchTechnician($name_or_area);
		echo json_encode(array("technicians" => $technicians, "success" => "true"));
	}

	public function insertCallSmsHistory()
	{
		
		$id_customer = $this->input->post('id_customer');
		$id_technician = $this->input->post('id_technician');
		$call_or_sms = $this->input->post('call_or_sms');

		if($id_customer == "" || $id_technician == "" || $call_or_sms == ""){
			exit();
		}else{
			$this->load->model('CallSmsHistoryModel');

			$data['id_registered_customer'] = $id_customer;
			$data['id_technician'] = $id_technician;
			$data['call_or_sms'] = $call_or_sms;

			if($this->CallSmsHistoryModel->insertCallSmsHistory($data)){
				echo "success";
			}else{
				echo 'fail';
			}
		}
	}

}

/* End of file  */
/* Location: ./application/controllers/ */