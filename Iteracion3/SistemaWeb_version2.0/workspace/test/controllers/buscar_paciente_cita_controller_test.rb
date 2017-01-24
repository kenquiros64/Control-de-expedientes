require 'test_helper'

class BuscarPacienteCitaControllerTest < ActionController::TestCase
  test "should get index" do
    get :index
    assert_response :success
  end

end
