require "json"
require "selenium-webdriver"
require "rspec"
include RSpec::Expectations

describe "AgregarObservacioncitasSpec" do

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
  
  it "test_agregar_observacioncitas_spec" do
    @driver.get(@base_url + "/medicos/sign_in")
    @driver.find_element(:id, "medico_email").clear
    @driver.find_element(:id, "medico_email").send_keys "admin@admin. com"
    @driver.find_element(:id, "medico_password").clear
    @driver.find_element(:id, "medico_password").send_keys "adminexpctlr"
    @driver.find_element(:name, "commit").click
    @driver.find_element(:link, "Citas").click
    @driver.find_element(:link, "Mostrar").click
    @driver.find_element(:link, "Observaciones").click
    @driver.find_element(:link, "Nueva observación").click
    @driver.find_element(:id, "observacion_descripcion").clear
    @driver.find_element(:id, "observacion_descripcion").send_keys "Observación cita"
    @driver.find_element(:name, "commit").click
    (@driver.find_element(:id, "notice").text).should == "Observación fue creada exitosamente."
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
