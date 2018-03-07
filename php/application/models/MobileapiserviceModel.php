<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class MobileapiserviceModel extends CI_Model {

	public $variable;

	public function __construct()
	{
		parent::__construct();
		
	}

	public function submitService($submitted_data)
	{
		return $this->db->insert('service_requests', $submitted_data);
	}

	public function uploadServiceImage($id_service_req,$image_name)
	{
		$data['id_service_request'] = $id_service_req;
		$data['image'] = $image_name;

		return $this->db->insert('service_images', $data);
	}

	public function getTechnicians()
	{
		$this->db->select('*');
		$this->db->from('technician');
		$this->db->limit(50);
		$this->db->where('publication_status', 'Enable');
		return $this->db->get()->result_array();
	}

	public function searchTechnician($name_or_area)
	{
		$this->db->select('*');
		$this->db->from('technician');
		$this->db->limit(20);
		$this->db->where("(name LIKE '%".$name_or_area."%' OR working_area LIKE '%".$name_or_area."%')", NULL, FALSE);
		$this->db->where('publication_status', 'Enable');
		return $this->db->get()->result_array();
	}

}

/* End of file  */
/* Location: ./application/models/ */