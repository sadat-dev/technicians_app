<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');



class MobileapiModel extends CI_Model {

	public $variable;

	public function __construct()
	{
		parent::__construct();
	}

	public function getItemImages($id_item)
	{
		$this->db->select('*');
		$this->db->from('item_images');
		return $this->db->get()->result_array();
	}

	public function updateInfoOnReRegister($id_user, $data)
	{
		$this->db->where('id_user',$id_user);
		return $this->db->update('registered_customer',$data);
	}
	
	public function checkEmailExist($email)
	{
		$this->db->select('id_user');
		$this->db->from('registered_customer');
		$this->db->where('email', $email);
		return $this->db->get()->row_array();
	}
	
	public function insert_token($token_data)
	{
		return $this->db->insert('pass_recover_tokens', $token_data);
	}
	
	public function getIdByEmail($email)
	{
		$this->db->select('id_user');
		$this->db->from('registered_customer');
		$this->db->where('email', $email);
		$this->db->limit(1);
		$result = $this->db->get()->row_array();
		return $result;
	}


	public function getTokenDetails($token)
	{
		$this->db->select('*');
		$this->db->from('pass_recover_tokens');
		$this->db->where('token', $token);
		$this->db->limit(1);
		$result = $this->db->get()->row_array();
		return $result;
	}


	public function updatePassword($id, $email, $password)
	{
		$query = "UPDATE `registered_customer` SET `password`= '$password' WHERE `id_user` = '$id' AND `email` = '$email' LIMIT 1";
		return  $this->db->query($query);
	}

	public function updateToken($token)
	{
		$query = "UPDATE `pass_recover_tokens` SET `status`= 'invalid' WHERE `token` = '$token' LIMIT 1";
		return  $this->db->query($query);		
	}


	public function getTopItems()
	{
		$this->db->select('
			item.id_item,item.id_subcategory,item.name,item.image,item.specification,item.description, item.description2,item.offer, item.selling_price,item.quantity,
			item.price,item.price_value
			');		
		$this->db->from('top_items');
		$this->db->join('item', 'top_items.id_top_item = item.id_item', 'left');
		return $this->db->get()->result_array();
	}
	
	
	public function logIn($email, $password)
	{
		$this->db->select('email, password')->from('registered_customer')->where('email', $email)->where('password', $password)->where('status','published')->limit(1);
		return $this->db->get()->row_array();
	}
	
	public function getUserDetailsAfterLogin($email)
	{
		$this->db->select('id_user,name, phone, email');
		$this->db->from('registered_customer');
		$this->db->where('email', $email);
		$this->db->limit(1);
		return $this->db->get()->row_array();
	}

	public function itemSearchByWord($searched_word)
	{
		$queryStirng =  "SELECT `id_item`,`id_subcategory`,`name`,`image`,`specification`,`description`,`description2`,`offer`,`selling_price`,`quantity`,
		`price`,`price_value` FROM `item` WHERE name LIKE '%".$searched_word."%' AND quantity > 0";
		$query = $this->db->query($queryStirng);
		return $query->result_array();
	}

	public function moreMenu()
	{
		$queryStirng = "SELECT `id_category`,`category_name`,`image` FROM `category` WHERE id_category > 9";
		$query = $this->db->query($queryStirng);
		return $query->result_array();
	}

	public function itemBySubcategory($id_subcategory)
	{
		$queryStirng =  "SELECT `id_item`,`id_subcategory`,`name`,`image`,`description`,`selling_price`,`quantity`
		 FROM `item` WHERE id_subcategory = '$id_subcategory' AND quantity > 0";
		$query = $this->db->query($queryStirng);
		return $query->result_array();
	}

	public function subCategoryByCategory($id_category)
	{
		$queryStirng =  "SELECT `id_subcategory`,`id_category`,`subcategory_name`,`image`,`remark` FROM `subcategory` WHERE id_category = '$id_category'";
		$query = $this->db->query($queryStirng);
		return $query->result_array();  // for get a single row result always use row_array()
	}

	public function insertCustomer($name,$email,$phone,$shipping_address)
	{
		$queryStirng = "INSERT INTO `customer` (`name`, `email`, `phone`, `shipping_address`) VALUES ('$name', '$email', '$phone', 
			'$shipping_address')";
$this->db->query($queryStirng);
$insert_id = $this->db->insert_id();
return  $insert_id;
}

public function insertOrder($customer_id, $amount, $quantity, $extra_message, $device_id, $shipping_cost)
{
	$queryStirng = "INSERT INTO `orders` (`id_customer`, `total_amount`, `total_quantity`, `message`, `device_id`, `shipping_cost`) VALUES ('$customer_id', '$amount', '$quantity', '$extra_message', '$device_id', '$shipping_cost')";
$this->db->query($queryStirng);
$insert_id = $this->db->insert_id();
return  $insert_id;
}

public function insertOrderedItems($id_order, $id_of_item, $id_subcat, $id_cat, $quantity)
{
	$queryStirng = "INSERT INTO `ordered_items` (`id_order`, `id_item`, `id_sub_category`, `id_category`, `quantity`) VALUES ('$id_order', '$id_of_item', '$id_subcat', '$id_cat', '$quantity')";
	return $this->db->query($queryStirng);
}

public function insertContacts($name, $number)
{
	$queryStirng = "INSERT INTO `phone_contacts` (`name`, `number`) VALUES ('$name', '$number')";
	return $this->db->query($queryStirng);
}

public function orderedItemsArray($id_order)
{
	$query = $this->db->query("SELECT `item`.name, `item`.image,`item`.selling_price AS price_value, `ordered_items`.quantity, (`item`.selling_price* `ordered_items`.quantity) AS item_wise_price FROM `ordered_items`
		LEFT   JOIN item ON `item`.id_item = `ordered_items`.id_item WHERE id_order = '$id_order'");
	return $query->result_array();
}

public function Signup($data)
{
	$this->db->insert('registered_customer', $data);
	$insert_id = $this->db->insert_id();
	return  $insert_id;
}

public function getOrdersSummaryByPhoneID($deviceId)
{
	$queryStirng = "SELECT `orders`.id_order, SUM(`item`.selling_price* `ordered_items`.quantity) AS `total_amount`, `orders`.total_quantity,`orders`.status, `orders`.order_date,`orders`.`shipping_cost` FROM `ordered_items` LEFT JOIN item ON `item`.id_item = `ordered_items`.id_item LEFT JOIN orders ON `orders`.id_order = `ordered_items`.id_order WHERE `orders`.device_id = '$deviceId' GROUp BY `orders`.id_order";
	$query = $this->db->query($queryStirng);
	return $query->result_array();
}
}



/* End of file MobileapiModel.php */

/* Location: ./application/models/MobileapiModel.php */