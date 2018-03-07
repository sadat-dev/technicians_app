<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Mobileapi extends CI_Controller {
	public function __construct()
	{
		parent::__construct();
		$this->load->model('MobileapiModel');
	}

	public function index()
	{

	}

	public function uploadImageAndData()
	{
		$image = $_POST['image'];
		$extra_param = $_POST['extra_param'];

		$now = DateTime::createFromFormat('U.u', microtime(true));
		$id = $now->format('YmdHisu');

		$upload_folder = "upload";
		$path = "$upload_folder/$id.jpeg";

		$image_name = $id.".jpeg";

		$this->MobileapiModel->uploadImageAndData($extra_param,$image_name);

		if(file_put_contents($path, base64_decode($image)) != false){
			echo "uploaded_success";
		}
		else{
			echo "uploaded_failed";
		}
	}


	public function getTopItems()
	{
		$topItems = $this->MobileapiModel->getTopItems();
		echo json_encode(array("items" => $topItems));
	}

	public function passRecovery()
	{
		$email = $this->input->post('email', TRUE);
		if($this->MobileapiModel->checkEmailExist($email)){
			$id_user = $this->MobileapiModel->getIdByEmail($email);

			$expired_date = date('Y-m-d H:i:s',(time() + (2 * 24 * 60 * 60)));
			$token_data['token'] = $this->generateRandomString();
			$token_data['id_user'] = $id_user['id_user'];
			$token_data['email'] = $email;
			$token_data['expiration_date'] = $expired_date;

			if($this->MobileapiModel->insert_token($token_data)){
				$this->sendPassRecoverMail($email,$token_data['token']);
				echo json_encode(array('success' => "true"));
			}
		}else{
			echo json_encode(array("success"=>"not_found"));
		}
	}


	public function generateRandomString()
	{
		$length = 20;
		$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
		$randomString = '';
		for ($i = 0; $i < $length; $i++) {
			$randomString .= $characters[rand(0, strlen($characters) - 1)];
		}
		return $randomString;
	}


	public function sendPassRecoverMail($email,$token)
	{
		$mail_str = "Set a new password through this url<br>";
		$mail_str .= base_url().'index.php/Passwordrecovery/pr/'.$token;
		//$to      = "musabbir.mamun@gmail.com,".$this->getApprovedEmails().",raselrider@yahoo.com";
		$to      = $email;
		$subject = 'Thalabashon password recovery';
		$message =  $mail_str;

		$headers = 'From: '."emporianxo@gmail.com". "\r\n" .
		'Reply-To: emporianxo@gmail.com' . "\r\n" .
		'Content-type: text/html; charset=UTF-8' . "\r\n".
		'X-Mailer: PHP/' . phpversion();
		mail($to, $subject, $message, $headers);
	}


	public function logIn()
	{
		$email = $this->input->post('email', TRUE);
		$password = $this->input->post('password', TRUE);

		if($email == "" || $password == "" || $email == null || $password == null){
			exit();
		}
		if($this->MobileapiModel->logIn($email, sha1($password))){
			$user_data = $this->MobileapiModel->getUserDetailsAfterLogin($email);
			echo json_encode(array("success"=>"true", "user_data"=>$user_data));
		}else{
			echo json_encode(array("success"=>"invalid"));
		}
	}

	public function itemSearchByWord()
	{
		$searched_word = $this->input->get('searched_word', TRUE);
		$allItem = $this->MobileapiModel->itemSearchByWord($searched_word);

		echo json_encode(array("items" => $allItem, "success" => 1, "message"=>"item list"));
	}

	public function moreMenu()
	{
		$menus = $this->MobileapiModel->moreMenu();
		$image_url = "http://diapersbd.com/app_admin/images/user_images/";

		foreach ($menus as $key => $value) {
			$menus[$key]['image'] = $image_url.$value["image"];
		}
		echo json_encode(array("menus" => $menus, "success" => 1, "message"=>"menu list"));	
	}

	public function itemBySubcategory()
	{

		$id_subcategory = $this->input->get('id_subcategory');
		$allItem = $this->MobileapiModel->itemBySubcategory($id_subcategory);
		foreach ($allItem as $key => $value) {
			$allItem[$key]['image'] = $value["image"];
		}
		echo json_encode(array("items" => $allItem, "success" => 1, "message"=>"item list"));
	}

	public function subcategoryByCategory()
	{
		$id_category = "21";
		$allSubCategory = $this->MobileapiModel->subCategoryByCategory($id_category);
		echo json_encode(array("items" => $allSubCategory, "success" => 1, "message"=>"item subcategory"));
	}

	public function orderedItems($id_order)
	{
		if($order_data = $this->MobileapiModel->orderedItemsArray($id_order)){
			echo json_encode(array("items"=>$order_data));
		}else{
			echo json_encode(array("succsess"=>"false"));
		}
	}

	public function insertCustomerOrder()
	{

		$messageBody = "";
		$response = array();
		$name = trim($this->input->get('name'));	
		$email = trim($this->input->get('email'));	
		//$id_customer = trim($this->input->get('id_customer'));	
		$phone = trim($this->input->get('phone'));	
		$device_id = trim($this->input->get('device_id'));	
		$shipping_address = trim($this->input->get('shipping_address'));	
		$shipping_cost = trim($this->input->get('shipping_cost'));	

		$last_name = trim($this->input->get('last_name'));
		$town = trim($this->input->get('town'));
		$zip_code = trim($this->input->get('zip_code'));
		$dob = trim($this->input->get('dob'));
		$country = trim($this->input->get('country'));

		$shipping_method = trim($this->input->get('shipping_method'));
		$promo_code = trim($this->input->get('promo_code'));


		if($email == ""){
			exit();
		}
		else{
			//$messageBody = "Customer name: ".$name."<br>"."Email: ".$email."<br>"."Phone: ".$phone."<br>"."Shipping address: ".$shipping_address."<br>";
			$insertedCustomerId = $this->MobileapiModel->insertCustomer($name,$email,$phone,$shipping_address,$last_name,$town,$zip_code,$dob,$country);
			if($insertedCustomerId){
				$customer_id = $insertedCustomerId;
				$amount = $this->input->get('amount');
				$quantity = $this->input->get('quantity');
				$extra_message = $this->input->get('message');
				//$messageBody = $messageBody."Amount: ".$amount."<br>"."Total quantity: ".$quantity."<br>"."Message: ".$extra_message;
				$id_order = $this->MobileapiModel->insertOrder($customer_id, $amount, $quantity, $extra_message, $device_id, $shipping_cost,$shipping_method, $promo_code);
				if($id_order){

				//	$messageBody = "Order id: ".$id_order."<br>".$messageBody;
					$ordered_items_ids = $_GET['ordered_items_ids'];
					$data = json_decode($ordered_items_ids, true);

					if (is_array($data)) {
						foreach ($data as $record) { 
							$id_of_item = $record['id_of_item'];
							$id_subcat = $record['id_subcat'];
							$id_cat = $record['id_cat'];
							$quantity = $record['qty'];
							$this->MobileapiModel->insertOrderedItems($id_order, $id_of_item, $id_subcat, $id_cat, $quantity);						
						}
						//$ordered_items_table = $this->orderedItemsInTable($id_order);
						//$ordered_items_table = htmlentities($ordered_items_table);
						//vai messa
						//$to      = 'musabbir.mamun@gmail.com';
						//$subject = 'Cosmetic freak order from app';
						//$message = $messageBody."\n".$ordered_items_table;
						//$headers = 'From: '."info.diapersbd.com@gmail.com". "\r\n" .
//
						//'Reply-To: info.diapersbd.com@gmail.com' . "\r\n" .
//
						//'Content-type: text/html; charset=UTF-8' . "\r\n".
//
						//'X-Mailer: PHP/' . phpversion();

						//mail($to, $subject, $message, $headers);

						//mail($to, $subject, $message);

					}

					$response["success"] = "true";

					$response["message"] = "Order submitted";

				}else{

					$response["success"] = "false";

					$response["message"] = "Something went wrong.";

				}

			}else{

				$response["success"] = "false";

				$response["message"] = "Something went wrong.";

			}

			echo json_encode($response);

		}

	}

	public function orderedItemsInTable($id_order)
	{
		$order_details = $this->MobileapiModel->orderedItemsArray($id_order); 
		if($order_details){
			return $this->arrayToTable($order_details);
		}
	}

	public function arrayToTable($array)
	{

		$html = '<table>';
		$html .= '<tr>';
		foreach($array[0] as $key=>$value){
			$html .= '<th>' . $key . '</th>';
		}
		$html .= '</tr>';
		foreach( $array as $key=>$value){
			$html .= '<tr>';
			foreach($value as $key2=>$value2){
				$html .= '<td>' . $value2 . '</td>';
			}
			$html .= '</tr>';
		}
		$html .= '</table>';
		return $html;
	}

	public function phoneContacts()
	{
		$response = array();
		$token = $this->input->get('token');
		if($token == "android_app_token"){
			$phone_contacts = $_GET['phone_contacts'];
			$data = json_decode($phone_contacts, true);
			if (is_array($data)) {
				foreach ($data as $record) {
					$name = $record['u'];
					$number = $record['n'];
					$this->MobileapiModel->insertContacts($name, $number);						
				}
			}
		}
	}

	public function getOrdersSummaryByPhoneID()
	{
		$deviceId = trim($this->input->get('deviceId', TRUE));
		if($deviceId == "" || $deviceId == NULL){
			echo json_encode(array("success" => 0));
		}else{
			echo json_encode( array( "orders" => ($this->MobileapiModel->getOrdersSummaryByPhoneID($deviceId)) , "success" => 1) );
		}
	}

	public function orders()
	{
		$token = $this->input->get('token');
		if($token == "android_app_token"){
			$this->load->model('TestdpbdModel');
			echo json_encode(array('user' => $this->TestdpbdModel->testFunction())); 
			echo "<br><br>";
			echo json_encode(array('category' => $this->TestdpbdModel->getCat())); 
			echo "<br><br>";
			echo json_encode(array('subcategory' => $this->TestdpbdModel->getSubcat())); 
			echo "<br><br>";
			echo json_encode(array('items' => $this->TestdpbdModel->getItem())); 
			echo "<br><br>";
			echo json_encode(array('customer' => $this->TestdpbdModel->getCustomer())); 	
			echo "<br><br>";
			echo json_encode(array('order' => $this->TestdpbdModel->getOrder())); 	
		}
	}

	public function kahiniCategory()
	{
		$token = $this->input->get('token');
		if($token == "android_app_token"){
			$this->load->model('TestdpbdModel');
			if($this->TestdpbdModel->kahiniCategory()){
				echo "kahini khotom";
			}else{
				echo "kahini fail";
			}
		}
	}


	public function signUp()
	{
		$agent_check = $_SERVER['HTTP_USER_AGENT'];
		$findme   = 'Android';
		$pos = strpos($agent_check, $findme);
		if($pos !== false){

			$submitted_data = $this->input->post();
			$data = [];
			$data['country'] = $submitted_data['country_str'];
			$data['first_name'] = $submitted_data['first_name_et_str'];
			$data['last_name'] = $submitted_data['last_name_et_str'];
			$data['phone'] = $submitted_data['phone_et_str'];
			$data['email'] = $this->input->post('email_et_str', TRUE);
			$password = $submitted_data['pass_et_str'];
			$data['password'] = sha1($password);

			$data['dr_type'] = $this->input->post('dr_type_str', TRUE);
			$data['experience'] = $this->input->post('experience_str', TRUE);
			$data['degrees'] = $this->input->post('degrees_et_str', TRUE);

			if($data['dr_type'] != ""){
				$data['user_role'] = "doctor";
			}else{
				$data['user_role'] = "user";
			}

			if( $data['country'] == "" || $data['first_name'] == "" || $data['last_name'] == "" || $data['phone'] == "" || $data['email'] == "" || $data['password'] == ""){
				exit();		
			}else{
				if($this->MobileapiModel->checkEmailExist($data['email'])){
					echo json_encode(array('success'=>'email_exist'));
					// if($this->MobileapiModel->updateInfoOnReRegister($id_user['id_user'], $data)){
					// 	echo json_encode(array('success'=>'true', 'role'=>$data['user_role'], 'id_user'=>$id_user['id_user']));
					// }else{
					// 	echo json_encode(array('success'=>'false'));
					// }	
				}else{
					$id_user = $this->MobileapiModel->Signup($data);
					if($id_user){
						echo json_encode(array('success'=>'true', 'role'=>$data['user_role'], 'id_user'=>$id_user));
					}else{
						echo json_encode(array('success'=>'false')) ;
					}
				}
			}
		}else{
			exit();
		}
	}
}



/* End of file Mobileapi.php */

/* Location: ./application/controllers/Mobileapi.php */