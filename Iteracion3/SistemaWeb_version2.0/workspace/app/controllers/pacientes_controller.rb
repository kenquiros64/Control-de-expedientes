class PacientesController < ApplicationController
  
  before_action :authenticate_medico!
  before_action :set_paciente, only: [:show, :edit, :update, :destroy]
  before_action :authorize_medico, only: [:show, :edit, :update, :destroy]

  # GET /pacientes
  # GET /pacientes.json
  def index
    @pacientes = current_medico.pacientes.paginate(page: params[:page], :per_page => 25)
  end

  # GET /pacientes/1
  # GET /pacientes/1.json
  def show 
      @telefonos = Telefono.where(:paciente_id => params[:id]) 
      @emails = Email.where(:paciente_id => params[:id]) 
      @observacions = @paciente.observacions 
      @enfermedads = @paciente.enfermedads
  end
  

  # GET /pacientes/new
  def new
    @paciente = Paciente.new
  end

  # GET /pacientes/1/edit
  def edit
  end

  # POST /pacientes
  # POST /pacientes.json
  def create
    @paciente = Paciente.new(paciente_params)
    
    respond_to do |format|
      if @paciente.save
        if current_medico.pacientes.exists?(['cedula LIKE ?', "%#{params[:paciente][:cedula]}%"])
          format.html { render :new, notice: 'Cédula ya existente.' }
        else
          current_medico.pacientes << @paciente
          format.html { redirect_to @paciente, notice: 'Paciente fue creado exitosamente.' }
          format.json { render :show, status: :created, location: @paciente }
        end
      else
        format.html { render :new }
        format.json { render json: @paciente.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /pacientes/1
  # PATCH/PUT /pacientes/1.json
  def update
    respond_to do |format|
      if current_medico.pacientes.exists?(['cedula LIKE ?', "%#{params[:paciente][:cedula]}%"])
        if @paciente.update(paciente_params)
          format.html { redirect_to @paciente, notice: 'Paciente fue actualizado exitosamente.' }
          format.json { render :show, status: :ok, location: @paciente }
        else
          format.html { render :edit }
          format.json { render json: @paciente.errors, status: :unprocessable_entity }
        end
      else
        format.html { render :edit, notice: 'Cédula ya existente.' }
      end
    end
  end

  # DELETE /pacientes/1
  # DELETE /pacientes/1.json
  def destroy
    @paciente.image.destroy
    @paciente.citum.each do |citum|
      citum.observacions.each do |obs|
         Observacion.delete(obs)
      end
      citum.observacions.destroy_all
    end
    @paciente.citum.destroy_all
    @paciente.observacions.each do |obs|
       Observacion.delete(obs)
    end
    @paciente.observacions.destroy_all
    @paciente.enfermedads.destroy_all
    
    current_medico.pacientes.delete(@paciente)
    @paciente.destroy
    respond_to do |format|
      format.html { redirect_to pacientes_url,
      notice: 'Paciente fue eliminado exitosamente.' }
      format.json { head :no_content }
    end
  end
  


  private
    # Use callbacks to share common setup or constraints between actions.
    def set_paciente
      @paciente = Paciente.find(params[:id])
    end
    
    def authorize_medico
      if current_medico.pacientes.exists?(['id LIKE ?', "%#{params[:id]}%"])
      else
        respond_to do |format|
          format.html { redirect_to pacientes_path }
        end
      end
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def paciente_params
      params.require(:paciente).permit(:cedula, :nombre, :apellido1, :apellido2, :fechaNacimiento, :nacionalidad, :sexo, :fechaFallecimiento,:image, telefonos_attributes: [:id, :telefono, :_destroy], emails_attributes: [:id, :email, :_destroy])
    end
    
    
end
