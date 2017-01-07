require "json"
require "selenium-webdriver"
require "rspec"
include RSpec::Expectations

describe "EditarPacientesSpec" do

  before(:each) do
    @driver = Selenium::WebDriver.for :firefox
    @base_url = "http://localhost:3000/"
    @accept_next_alert = true
    @driver.manage.timeouts.implicit_wait = 30
    @verification_errors = []
  end
  
  after(:each) do
    @driver.quit
    @verification_errors.should == []
  end
  
  it "test_editar_pacientes_spec" do
    @driver.get(@base_url + "/medicos/sign_in")
    @driver.find_element(:id, "medico_email").clear
    @driver.find_element(:id, "medico_email").send_keys "admin@admin.com"
    @driver.find_element(:id, "medico_password").clear
    @driver.find_element(:id, "medico_password").send_keys "adminexpctlr"
    @driver.find_element(:name, "commit").click
    @driver.find_element(:link, "Pacientes").click
    @driver.find_element(:xpath, "(//a[contains(text(),'Editar')])[2]").click
    @driver.find_element(:id, "paciente_cedula").clear
    @driver.find_element(:id, "paciente_cedula").send_keys "1-1123-1223"
    Selenium::WebDriver::Support::Select.new(@driver.find_element(:id, "paciente_fechaNacimiento_1i")).select_by(:text, "2006")
    @driver.find_element(:name, "commit").click
    (@driver.find_element(:id, "notice").text).should == "Paciente fue actualizado exitosamente."
  end
  
  def element_present?(how, what)
    @driver.find_element(how, what)
    true
  rescue Selenium::WebDriver::Error::NoSuchElementError
    false
  end
  
  def alert_present?()
    @driver.switch_to.alert
    true
  rescue Selenium::WebDriver::Error::NoAlertPresentError
    false
  end
  
  def verify(&blk)
    yield
  rescue ExpectationNotMetError => ex
    @verification_errors << ex
  end
  
  def close_alert_and_get_its_text(how, what)
    alert = @driver.switch_to().alert()
    alert_text = alert.text
    if (@accept_next_alert) then
      alert.accept()
    else
      alert.dismiss()
    end
    alert_text
  ensure
    @accept_next_alert = true
  end
end
