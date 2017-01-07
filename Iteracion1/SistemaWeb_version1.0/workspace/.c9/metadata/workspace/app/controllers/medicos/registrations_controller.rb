{"filter":false,"title":"registrations_controller.rb","tooltip":"/app/controllers/medicos/registrations_controller.rb","undoManager":{"mark":41,"position":41,"stack":[[{"start":{"row":29,"column":20},"end":{"row":29,"column":30},"action":"remove","lines":["root_path "],"id":2},{"start":{"row":29,"column":20},"end":{"row":29,"column":48},"action":"insert","lines":["new_medico_registration_path"]}],[{"start":{"row":14,"column":4},"end":{"row":14,"column":50},"action":"remove","lines":["resource.passcode = params[:medico][:password]"],"id":3}],[{"start":{"row":13,"column":4},"end":{"row":30,"column":7},"action":"remove","lines":["build_resource(sign_up_params)","    ","    if resource.save","        yield resource if block_given?","        if resource.active_for_authentication?","            set_flash_message :notice, :signed_up if is_flashing_format?","            sign_up(resource_name, resource)","            respond_with resource, location: after_sign_up_path_for(resource)","        else","            set_flash_message :notice, :\"signed_up_but_#{resource.inactive_message}\" if is_flashing_format?","            expire_data_after_sign_in!","            respond_with resource, location: after_inactive_sign_up_path_for(resource)","        end","    else","        clean_up_passwords resource","        resource.errors.full_messages.each {|x| flash[x] = x} # Rails 4 simple way","        redirect_to new_medico_registration_path","    end"],"id":4},{"start":{"row":13,"column":4},"end":{"row":13,"column":5},"action":"insert","lines":["s"]}],[{"start":{"row":13,"column":5},"end":{"row":13,"column":6},"action":"insert","lines":["u"],"id":5}],[{"start":{"row":13,"column":6},"end":{"row":13,"column":7},"action":"insert","lines":["p"],"id":6}],[{"start":{"row":13,"column":7},"end":{"row":13,"column":8},"action":"insert","lines":["e"],"id":7}],[{"start":{"row":13,"column":8},"end":{"row":13,"column":9},"action":"insert","lines":["r"],"id":8}],[{"start":{"row":13,"column":9},"end":{"row":13,"column":10},"action":"insert","lines":[" "],"id":9}],[{"start":{"row":13,"column":10},"end":{"row":13,"column":11},"action":"insert","lines":["d"],"id":10}],[{"start":{"row":13,"column":11},"end":{"row":13,"column":12},"action":"insert","lines":["o"],"id":11}],[{"start":{"row":13,"column":10},"end":{"row":13,"column":12},"action":"remove","lines":["do"],"id":12},{"start":{"row":13,"column":10},"end":{"row":15,"column":7},"action":"insert","lines":["do","      ","    end"]}],[{"start":{"row":14,"column":6},"end":{"row":14,"column":7},"action":"insert","lines":["v"],"id":13}],[{"start":{"row":14,"column":6},"end":{"row":14,"column":7},"action":"remove","lines":["v"],"id":14}],[{"start":{"row":14,"column":6},"end":{"row":14,"column":52},"action":"insert","lines":["resource.passcode = params[:medico][:password]"],"id":15}],[{"start":{"row":28,"column":4},"end":{"row":34,"column":7},"action":"remove","lines":["pass = params[:medico][:password]","    ","    if params[:medico][:password].blank?","      resource.passcode = params[:medico][:current_password]","    else","      resource.passcode = params[:medico][:password]","    end"],"id":16}],[{"start":{"row":26,"column":4},"end":{"row":44,"column":7},"action":"remove","lines":["self.resource = resource_class.to_adapter.get!(send(:\"current_#{resource_name}\").to_key)","    prev_unconfirmed_email = resource.unconfirmed_email if resource.respond_to?(:unconfirmed_email)","    ","    ","    resource_updated = update_resource(resource, account_update_params)","    yield resource if block_given?","    if resource_updated","      if is_flashing_format?","        flash_key = update_needs_confirmation?(resource, prev_unconfirmed_email) ?","          :update_needs_confirmation : :updated","        set_flash_message :notice, flash_key","      end","      bypass_sign_in resource, scope: resource_name","      respond_with resource, location: after_update_path_for(resource)","    else","      clean_up_passwords resource","      set_minimum_password_length","      respond_with resource","    end"],"id":17}],[{"start":{"row":26,"column":4},"end":{"row":26,"column":5},"action":"insert","lines":["u"],"id":18}],[{"start":{"row":26,"column":5},"end":{"row":26,"column":6},"action":"insert","lines":["p"],"id":19}],[{"start":{"row":26,"column":6},"end":{"row":26,"column":7},"action":"insert","lines":["e"],"id":20}],[{"start":{"row":26,"column":7},"end":{"row":26,"column":8},"action":"insert","lines":["r"],"id":21}],[{"start":{"row":26,"column":7},"end":{"row":26,"column":8},"action":"remove","lines":["r"],"id":22}],[{"start":{"row":26,"column":6},"end":{"row":26,"column":7},"action":"remove","lines":["e"],"id":23}],[{"start":{"row":26,"column":5},"end":{"row":26,"column":6},"action":"remove","lines":["p"],"id":24}],[{"start":{"row":26,"column":4},"end":{"row":26,"column":5},"action":"remove","lines":["u"],"id":25}],[{"start":{"row":26,"column":4},"end":{"row":26,"column":5},"action":"insert","lines":["s"],"id":26}],[{"start":{"row":26,"column":5},"end":{"row":26,"column":6},"action":"insert","lines":["u"],"id":27}],[{"start":{"row":26,"column":6},"end":{"row":26,"column":7},"action":"insert","lines":["p"],"id":28}],[{"start":{"row":26,"column":7},"end":{"row":26,"column":8},"action":"insert","lines":["e"],"id":29}],[{"start":{"row":26,"column":8},"end":{"row":26,"column":9},"action":"insert","lines":["r"],"id":30}],[{"start":{"row":26,"column":9},"end":{"row":26,"column":10},"action":"insert","lines":[" "],"id":31}],[{"start":{"row":26,"column":10},"end":{"row":26,"column":11},"action":"insert","lines":["d"],"id":32}],[{"start":{"row":26,"column":11},"end":{"row":26,"column":12},"action":"insert","lines":["o"],"id":33}],[{"start":{"row":26,"column":10},"end":{"row":26,"column":12},"action":"remove","lines":["do"],"id":34},{"start":{"row":26,"column":10},"end":{"row":26,"column":12},"action":"insert","lines":["do"]}],[{"start":{"row":26,"column":12},"end":{"row":27,"column":0},"action":"insert","lines":["",""],"id":35},{"start":{"row":27,"column":0},"end":{"row":27,"column":6},"action":"insert","lines":["      "]}],[{"start":{"row":27,"column":4},"end":{"row":27,"column":6},"action":"remove","lines":["  "],"id":36}],[{"start":{"row":27,"column":4},"end":{"row":27,"column":5},"action":"insert","lines":["e"],"id":37}],[{"start":{"row":27,"column":5},"end":{"row":27,"column":6},"action":"insert","lines":["n"],"id":38}],[{"start":{"row":27,"column":6},"end":{"row":27,"column":7},"action":"insert","lines":["d"],"id":39},{"start":{"row":27,"column":2},"end":{"row":27,"column":4},"action":"remove","lines":["  "]}],[{"start":{"row":27,"column":2},"end":{"row":27,"column":4},"action":"insert","lines":["  "],"id":40}],[{"start":{"row":26,"column":12},"end":{"row":27,"column":0},"action":"insert","lines":["",""],"id":41},{"start":{"row":27,"column":0},"end":{"row":27,"column":6},"action":"insert","lines":["      "]}],[{"start":{"row":27,"column":6},"end":{"row":33,"column":7},"action":"insert","lines":["pass = params[:medico][:password]","    ","    if params[:medico][:password].blank?","      resource.passcode = params[:medico][:current_password]","    else","      resource.passcode = params[:medico][:password]","    end"],"id":42}],[{"start":{"row":29,"column":0},"end":{"row":29,"column":2},"action":"insert","lines":["  "],"id":43},{"start":{"row":30,"column":0},"end":{"row":30,"column":2},"action":"insert","lines":["  "]},{"start":{"row":31,"column":0},"end":{"row":31,"column":2},"action":"insert","lines":["  "]},{"start":{"row":32,"column":0},"end":{"row":32,"column":2},"action":"insert","lines":["  "]},{"start":{"row":33,"column":0},"end":{"row":33,"column":2},"action":"insert","lines":["  "]}]]},"ace":{"folds":[],"scrolltop":174.13345384597778,"scrollleft":0,"selection":{"start":{"row":29,"column":5},"end":{"row":33,"column":9},"isBackwards":false},"options":{"guessTabSize":true,"useWrapMode":false,"wrapToView":true},"firstLineState":{"row":11,"state":"start","mode":"ace/mode/ruby"}},"timestamp":1483592971761,"hash":"89bf3da1af433dcb92c2ce458e9d1ed88587bd6f"}