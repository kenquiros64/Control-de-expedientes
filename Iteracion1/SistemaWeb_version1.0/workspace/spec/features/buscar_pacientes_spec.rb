require "json"
require "selenium-webdriver"
require "rspec"
include RSpec::Expectations

describe "BuscarPacientesSpec" do

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
  
  it "test_buscar_pacientes_spec" do
    @driver.get(@base_url + "/medicos/sign_in")
    @driver.find_element(:id, "medico_email").clear
    @driver.find_element(:id, "medico_email").send_keys "admin@admin.com"
    @driver.find_element(:id, "medico_password").clear
    @driver.find_element(:id, "medico_password").send_keys "adminexpctlr"
    @driver.find_element(:name, "commit").click
    @driver.find_element(:link, "Pacientes").click
    @driver.find_element(:link, "Buscar").click
    @driver.find_element(:id, "keyword").clear
    @driver.find_element(:id, "keyword").send_keys "Am"
    @driver.find_element(:xpath, "//button[@type='submit']").click
    (@driver.find_element(:xpath, "//div[@id='main']/div[2]/table/tbody/tr/td[2]").text).should == "Am AP AP"
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
