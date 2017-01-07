class InicioController < ApplicationController
  before_action :authenticate_medico!
  def index
  end
end
